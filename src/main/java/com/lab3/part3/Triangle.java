package com.lab3.part3;

public class Triangle {
    protected double a, b, c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getPerimeter() {
        return a + b + c;
    }

    public double getArea() {
        double p = getPerimeter() / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public String getInfo() {
        return String.format("Треугольник (%.2f, %.2f, %.2f) | P=%.2f | S=%.2f",
                a, b, c, getPerimeter(), getArea());
    }
}