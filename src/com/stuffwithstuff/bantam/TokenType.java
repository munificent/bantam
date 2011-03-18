package com.stuffwithstuff.bantam;

public enum TokenType {
  LEFT_PAREN,
  RIGHT_PAREN,
  COMMA,
  ASSIGN,
  PLUS,
  MINUS,
  ASTERISK,
  SLASH,
  CARET,
  TILDE,
  BANG,
  QUESTION,
  COLON,
  NAME,
  EOF;
  
  /**
   * If the TokenType represents a punctuator (i.e. a token that can split an
   * identifier like '+', this will get its text.
   */
  public Character punctuator() {
    switch (this) {
    case LEFT_PAREN:  return '(';
    case RIGHT_PAREN: return ')';
    case COMMA:       return ',';
    case ASSIGN:      return '=';
    case PLUS:        return '+';
    case MINUS:       return '-';
    case ASTERISK:    return '*';
    case SLASH:       return '/';
    case CARET:       return '^';
    case TILDE:       return '~';
    case BANG:        return '!';
    case QUESTION:    return '?';
    case COLON:       return ':';
    default:          return null;
    }
  }
}
