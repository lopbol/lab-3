package com.lab3.part3;

public class Factorial {
    protected int n;

    public Factorial(int n) {
        this.n = n;
    }

    public long calculateEven() {
        long result = 1;
        for (int i = 2; i <= n; i += 2) {
            result *= i;
        }
        return result;
    }

    public long calculateOdd() {
        long result = 1;
        for (int i = 1; i <= n; i += 2) {
            result *= i;
        }
        return result;
    }

    public String getInfo() {
        return String.format("N=%d | Чётный факториал=%d | Нечётный факториал=%d",
                n, calculateEven(), calculateOdd());
    }
}