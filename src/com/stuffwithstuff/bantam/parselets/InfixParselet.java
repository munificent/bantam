package com.stuffwithstuff.bantam.parselets;

import com.stuffwithstuff.bantam.Parser;
import com.stuffwithstuff.bantam.Token;
import com.stuffwithstuff.bantam.expressions.Expression;

/**
 * One of the two parselet interfaces used by the Pratt parser. An
 * InfixParselet is associated with a token that appears in the middle of the
 * expression it parses. Its parse() method will be called after the left-hand
 * side has been parsed, and it in turn is responsible for parsing everything
 * that comes after the token. This is also used for postfix expressions, in
 * which case it simply doesn't consume any more tokens in its parse() call.
 */
public interface InfixParselet {
  Expression parse(Parser parser, Expression left, Token token);
  int getPrecedence();
}
