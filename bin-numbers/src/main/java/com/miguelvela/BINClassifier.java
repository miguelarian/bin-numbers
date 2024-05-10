package com.miguelvela;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BINClassifier {

    private String[][] binRanges;

    public BINClassifier(String[][] binRanges) {
        this.binRanges = binRanges;
    }

    public String classify(String card) {

        if(card == null || card == "") {
            return null;
        }
        String regex = "^\\d{4} \\d{4} \\d{4} \\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(card);

        if (!matcher.matches()) {
            return null;
        }

        String[] cardSegments = card.split(" ");
        String cardSanitized = String.join("", cardSegments);
        long bin = Long.parseLong(cardSanitized.substring(0, 10));


        return Arrays.stream(this.binRanges)
                .map(binRange -> {
                    String[] binRangeStartSegments = binRange[0].split(" ");
                    String binRangeStartSanitized = String.join("", binRangeStartSegments);
                    long binStart = Long.parseLong(binRangeStartSanitized);

                    String[] binRangeEndSegments = binRange[1].split(" ");
                    String binRangeEndSanitized = String.join("", binRangeEndSegments);
                    long binEnd = Long.parseLong(binRangeEndSanitized);

                    String schema = binRange[2];

                    if(bin >= binStart && bin <= binEnd) {
                        return schema;
                    }
                    return null;
                })
                .findFirst()
                .orElse(null);
    }
}
