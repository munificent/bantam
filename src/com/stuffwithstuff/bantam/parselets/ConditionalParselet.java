package com.stuffwithstuff.bantam.parselets;

import com.stuffwithstuff.bantam.Parser;
import com.stuffwithstuff.bantam.Precedence;
import com.stuffwithstuff.bantam.Token;
import com.stuffwithstuff.bantam.TokenType;
import com.stuffwithstuff.bantam.expressions.ConditionalExpression;
import com.stuffwithstuff.bantam.expressions.Expression;

/**
 * Parselet for the condition or "ternary" operator, like "a ? b : c".
 */
public class ConditionalParselet implements InfixParselet {
  public Expression parse(Parser parser, Expression left, Token token) {
    Expression thenArm = parser.parseExpression();
    parser.consume(TokenType.COLON);
    Expression elseArm = parser.parseExpression(Precedence.CONDITIONAL - 1);
    
    return new ConditionalExpression(left, thenArm, elseArm);
  }

  public int getPrecedence() {
    return Precedence.CONDITIONAL;
  }
}