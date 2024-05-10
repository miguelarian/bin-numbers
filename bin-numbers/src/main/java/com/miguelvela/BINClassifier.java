package com.miguelvela;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BINClassifier {

    private final String[][] binRanges;

    public BINClassifier(String[][] binRanges) {
        this.binRanges = binRanges;
    }

    public String classify(String card) {

        if (!validateCard(card)){
            return null;
        }

        long bin = getBin(card);

        return Arrays.stream(this.binRanges)
                .map(binRange -> {
                    long binStart = getBin(binRange[0]);
                    long binEnd = getBin(binRange[1]);
                    String schema = binRange[2];

                    if(bin >= binStart && bin <= binEnd) {
                        return schema;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .findAny()
                .orElse(null);
    }

    private static long getBin(String card) {
        String[] cardSegments = card.split(" ");
        String cardSanitized = String.join("", cardSegments);
        return Long.parseLong(cardSanitized.substring(0, 10));
    }

    private static boolean validateCard(String card) {
        if(card == null || card.isEmpty()) {
            return false;
        }
        String cardRegex = "^\\d{4} \\d{4} \\d{4} \\d{4}$";
        Pattern pattern = Pattern.compile(cardRegex);
        Matcher matcher = pattern.matcher(card);

        return matcher.matches();
    }
}
