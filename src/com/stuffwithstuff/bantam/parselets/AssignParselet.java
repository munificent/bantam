package com.stuffwithstuff.bantam.parselets;

import com.stuffwithstuff.bantam.ParseException;
import com.stuffwithstuff.bantam.Parser;
import com.stuffwithstuff.bantam.Precedence;
import com.stuffwithstuff.bantam.Token;
import com.stuffwithstuff.bantam.expressions.AssignExpression;
import com.stuffwithstuff.bantam.expressions.Expression;
import com.stuffwithstuff.bantam.expressions.NameExpression;

/**
 * Parses assignment expressions like "a = b". The left side of an assignment
 * expression must be a simple name like "a", and expressions are
 * right-associative. (In other words, "a = b = c" is parsed as "a = (b = c)").
 */
public class AssignParselet implements InfixParselet {
  public Expression parse(Parser parser, Expression left, Token token) {
    Expression right = parser.parseExpression(Precedence.ASSIGNMENT - 1);
    
    if (!(left instanceof NameExpression)) throw new ParseException(
        "The left-hand side of an assignment must be a name.");
    
    String name = ((NameExpression)left).getName();
    return new AssignExpression(name, right);
  }

  public int getPrecedence() { return Precedence.ASSIGNMENT; }
}