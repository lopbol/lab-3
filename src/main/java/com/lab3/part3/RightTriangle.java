package com.lab3.part3;

public final class RightTriangle extends Triangle {

    public RightTriangle(double a, double b, double c) {
        super(a, b, c);
    }

    public boolean isRight() {
        double a2 = a * a, b2 = b * b, c2 = c * c;
        return Math.abs(a2 + b2 - c2) < 0.001 ||
                Math.abs(a2 + c2 - b2) < 0.001 ||
                Math.abs(b2 + c2 - a2) < 0.001;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " | Прямоугольный: " + (isRight() ? "да" : "нет");
    }
}