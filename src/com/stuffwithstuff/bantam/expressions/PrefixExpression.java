package com.stuffwithstuff.bantam.expressions;

import com.stuffwithstuff.bantam.TokenType;

/**
 * A prefix unary arithmetic expression like "!a" or "-b".
 */
public class PrefixExpression implements Expression {
  public PrefixExpression(TokenType operator, Expression right) {
    mOperator = operator;
    mRight = right;
  }
  
  public void print(StringBuilder builder) {
    builder.append("(").append(mOperator.punctuator());
    mRight.print(builder);
    builder.append(")");
  }

  private final TokenType  mOperator;
  private final Expression mRight;
}
