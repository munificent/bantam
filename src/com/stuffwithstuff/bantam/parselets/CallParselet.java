package com.stuffwithstuff.bantam.parselets;

import java.util.ArrayList;
import java.util.List;

import com.stuffwithstuff.bantam.Parser;
import com.stuffwithstuff.bantam.Precedence;
import com.stuffwithstuff.bantam.Token;
import com.stuffwithstuff.bantam.TokenType;
import com.stuffwithstuff.bantam.expressions.CallExpression;
import com.stuffwithstuff.bantam.expressions.Expression;

/**
 * Parselet to parse a function call like "a(b, c, d)".
 */
public class CallParselet implements InfixParselet {
  public Expression parse(Parser parser, Expression left, Token token) {
    // Parse the comma-separated arguments until we hit, ")".
    List<Expression> args = new ArrayList<Expression>();
    
    // There may be no arguments at all.
    if (!parser.match(TokenType.RIGHT_PAREN)) {
      do {
        args.add(parser.parseExpression());
      } while (parser.match(TokenType.COMMA));
      parser.consume(TokenType.RIGHT_PAREN);
    }
    
    return new CallExpression(left, args);
  }

  public int getPrecedence() {
    return Precedence.CALL;
  }
}