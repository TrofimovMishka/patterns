package org.example.strategy;

public interface PayStrategy {
    boolean pay(int amount);

    void collectPaymentDetails();
}
