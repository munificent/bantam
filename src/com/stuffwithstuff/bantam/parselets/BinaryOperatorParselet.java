package com.stuffwithstuff.bantam.parselets;

import com.stuffwithstuff.bantam.Parser;
import com.stuffwithstuff.bantam.Token;
import com.stuffwithstuff.bantam.expressions.Expression;
import com.stuffwithstuff.bantam.expressions.OperatorExpression;

/**
 * Generic infix parselet for a binary arithmetic operator. The only
 * difference when parsing, "+", "-", "*", "/", and "^" is precedence and
 * associativity, so we can use a single parselet class for all of those.
 */
public class BinaryOperatorParselet implements InfixParselet {
  public BinaryOperatorParselet(int precedence, boolean isRight) {
    mPrecedence = precedence;
    mIsRight = isRight;
  }
  
  public Expression parse(Parser parser, Expression left, Token token) {
    // To handle right-associative operators like "^", we allow a slightly
    // lower precedence when parsing the right-hand side. This will let a
    // parselet with the same precedence appear on the right, which will then
    // take *this* parselet's result as its left-hand argument.
    Expression right = parser.parseExpression(
        mPrecedence - (mIsRight ? 1 : 0));
    
    return new OperatorExpression(left, token.getType(), right);
  }

  public int getPrecedence() {
    return mPrecedence;
  }
  
  private final int mPrecedence;
  private final boolean mIsRight;
}