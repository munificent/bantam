package com.stuffwithstuff.bantam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.stuffwithstuff.bantam.expressions.Expression;
import com.stuffwithstuff.bantam.parselets.InfixParselet;
import com.stuffwithstuff.bantam.parselets.PrefixParselet;

public class Parser {
  public Parser(Iterator<Token> tokens) {
    mTokens = tokens;
  }
  
  public void register(TokenType token, PrefixParselet parselet) {
    mPrefixParselets.put(token, parselet);
  }
  
  public void register(TokenType token, InfixParselet parselet) {
    mInfixParselets.put(token, parselet);
  }
  
  public Expression parseExpression(int stickiness) {
    Token token = consume();
    PrefixParselet prefix = mPrefixParselets.get(token.getType());
    
    if (prefix == null) throw new ParseException("Could not parse \"" +
        token.getText() + "\".");
    
    Expression left = prefix.parse(this, token);
    
    while (stickiness < getStickiness()) {
      token = consume();
      
      InfixParselet infix = mInfixParselets.get(token.getType());
      left = infix.parse(this, left, token);
    }
    
    return left;
  }
  
  public Expression parseExpression() {
    return parseExpression(0);
  }
  
  public boolean match(TokenType expected) {
    Token token = lookAhead(0);
    if (token.getType() != expected) {
      return false;
    }
    
    consume();
    return true;
  }
  
  public Token consume(TokenType expected) {
    Token token = lookAhead(0);
    if (token.getType() != expected) {
      throw new RuntimeException("Expected token " + expected +
          " and found " + token.getType());
    }
    
    return consume();
  }
  
  public Token consume() {
    // Make sure we've read the token.
    lookAhead(0);
    
    return mRead.remove(0);
  }
  
  private Token lookAhead(int distance) {
    // Read in as many as needed.
    while (distance >= mRead.size()) {
      mRead.add(mTokens.next());
    }

    // Get the queued token.
    return mRead.get(distance);
  }

  private int getStickiness() {
    int stickiness = 0;

    InfixParselet parser = mInfixParselets.get(lookAhead(0).getType());
    if (parser != null) {
      stickiness = parser.getPrecedence();
    }
    
    return stickiness;
  }
  
  private final Iterator<Token> mTokens;
  private final List<Token> mRead = new ArrayList<Token>();
  private final Map<TokenType, PrefixParselet> mPrefixParselets =
      new HashMap<TokenType, PrefixParselet>();
  private final Map<TokenType, InfixParselet> mInfixParselets =
      new HashMap<TokenType, InfixParselet>();
}
