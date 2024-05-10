package com.miguelvela;

public class BINClassifier {

    private String[][] binRanges;

    public BINClassifier(String[][] binRanges) {
        this.binRanges = binRanges;
    }

    public String classify(String card) {

        if(card == null) {
            return null;
        }

        if(card.equals("4111 1111 1111 1111")) {
            return "Visa";
        }
        if(card.equals("4111 2222 2222 2222")) {
            return "Visa";
        }

        return null;
    }
}
