package org.example.strategy;

import org.example.CreditCard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CardPay implements PayStrategy {

    private static Collection<CreditCard> cards = Collections.synchronizedCollection(new ArrayList<>());
    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private CreditCard card;

    static {
        DateFormat dateFormat = new SimpleDateFormat("MM/yy");
        CreditCard card_1 = new CreditCard("8328-9329-8329-8433", dateFormat.format(LocalDate.now().minusYears(4)), "123");
        CreditCard card_2 = new CreditCard("4433-9329-2332-8433", dateFormat.format(LocalDate.now().minusYears(3)), "332");
        CreditCard card_3 = new CreditCard("8755-1236-8329-7765", dateFormat.format(LocalDate.now().minusYears(1)), "444");

        card_1.setAmount(1289898);
        card_2.setAmount(89898);
        card_3.setAmount(18);

        cards.add(card_1);
        cards.add(card_2);
        cards.add(card_3);
    }


    @Override
    public boolean pay(int amount) {
        if (card != null && card.getAmount() >= amount) {
            cards.stream()
                    .filter(inCard -> inCard.equals(card))
                    .findFirst()
                    .ifPresent(inCard -> inCard.setAmount(inCard.getAmount() - amount));
            return true;
        }
        return false;
    }

    @Override
    public void collectPaymentDetails() {
        try {
            System.out.print("Enter the card number: ");
            String number = READER.readLine();
            System.out.print("Enter the card expiration date 'MM/yy': ");
            String date = READER.readLine();
            String cvv = READER.readLine();
            card = new CreditCard(number, date, cvv);

            if (!cards.contains(card)) {
                card = null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}