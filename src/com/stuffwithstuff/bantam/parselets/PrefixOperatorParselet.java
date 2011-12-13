package com.stuffwithstuff.bantam.parselets;

import com.stuffwithstuff.bantam.Parser;
import com.stuffwithstuff.bantam.Token;
import com.stuffwithstuff.bantam.expressions.Expression;
import com.stuffwithstuff.bantam.expressions.PrefixExpression;

/**
 * Generic prefix parselet for an unary arithmetic operator. Parses prefix
 * unary "-", "+", "~", and "!" expressions.
 */
public class PrefixOperatorParselet implements PrefixParselet {
  public PrefixOperatorParselet(int precedence) {
    mPrecedence = precedence;
  }
  
  public Expression parse(Parser parser, Token token) {
    // To handle right-associative operators like "^", we allow a slightly
    // lower precedence when parsing the right-hand side. This will let a
    // parselet with the same precedence appear on the right, which will then
    // take *this* parselet's result as its left-hand argument.
    Expression right = parser.parseExpression(mPrecedence);
    
    return new PrefixExpression(token.getType(), right);
  }

  public int getPrecedence() {
    return mPrecedence;
  }
  
  private final int mPrecedence;
}