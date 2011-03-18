package com.stuffwithstuff.bantam.expressions;

/**
 * A simple variable name expression like "abc".
 */
public class NameExpression implements Expression {
  public NameExpression(String name) {
    mName = name;
  }
  
  public String getName() { return mName; }
  
  public void print(StringBuilder builder) {
    builder.append(mName);
  }
  
  private final String mName;
}
