package com.stuffwithstuff.bantam.parselets;

import com.stuffwithstuff.bantam.Parser;
import com.stuffwithstuff.bantam.Token;
import com.stuffwithstuff.bantam.TokenType;
import com.stuffwithstuff.bantam.expressions.Expression;

/**
 * Parses parentheses used to group an expression, like "a * (b + c)".
 */
public class GroupParselet implements PrefixParselet {
  public Expression parse(Parser parser, Token token) {
    Expression expression = parser.parseExpression();
    parser.consume(TokenType.RIGHT_PAREN);
    return expression;
  }
}
