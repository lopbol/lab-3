package com.lab3.part3;

public final class FactorialCalculator extends Factorial {

    public FactorialCalculator(int n) {
        super(n);
    }

    @Override
    public String getInfo() {
        return "Факториалы для N=" + n + ":\n" +
                "   Чётный факториал (2*4*...*" + (n % 2 == 0 ? n : n-1) + ") = " + calculateEven() + "\n" +
                "   Нечётный факториал (1*3*...*" + (n % 2 == 1 ? n : n-1) + ") = " + calculateOdd();
    }
}