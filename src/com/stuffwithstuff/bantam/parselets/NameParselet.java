package com.stuffwithstuff.bantam.parselets;

import com.stuffwithstuff.bantam.Parser;
import com.stuffwithstuff.bantam.Token;
import com.stuffwithstuff.bantam.expressions.Expression;
import com.stuffwithstuff.bantam.expressions.NameExpression;

/**
 * Simple parselet for a named variable like "abc".
 */
public class NameParselet implements PrefixParselet {
  public Expression parse(Parser parser, Token token) {
    return new NameExpression(token.getText());
  }
}
