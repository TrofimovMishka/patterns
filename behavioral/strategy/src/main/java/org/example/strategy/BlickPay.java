package org.example.strategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BlickPay implements PayStrategy {

    private static final Map<String, String> DATA_BASE = new HashMap<>();
    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private String phone;
    private String blickCode;
    private boolean signedIn;

    static {
        DATA_BASE.put("+48123456789", "889234");
        DATA_BASE.put("+48987654321", "992922");
    }

    @Override
    public boolean pay(int amount) {
        if(signedIn){
            System.out.println("Paying " + amount + " using blick code");
            return true;
        }
        return false;
    }

    @Override
    public void collectPaymentDetails() {
        try{
            while (!signedIn) {
                System.out.print("Enter phone: ");
                phone = READER.readLine();
                System.out.println("enter the blickCode: ");
                blickCode = READER.readLine();
                if(isDataCorrect()){
                    System.out.println("Verified");
                }
                else {
                    System.out.println("Provided data is not correct");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isDataCorrect() {
        String blick = DATA_BASE.get(phone);
        setSignedIn(blick != null && blickCode.equals(blick));
        return signedIn;
    }

    private void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }
}
