package com.example.JSFLab.mbeans;

public class Square implements SquareMBean {
  private double square;

  public void calculateSquare(double r) {
    this.square = r*r/2 + Math.PI*r*r/4 + r*r/2;
  }

  @Override
  public double getSquare() {
    return this.square;
  }
}
