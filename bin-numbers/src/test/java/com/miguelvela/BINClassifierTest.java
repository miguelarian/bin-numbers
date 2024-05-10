package com.miguelvela;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class BINClassifierTest {

    @ParameterizedTest
    @NullAndEmptySource
    void classify__nullCard_returnsNull(String card) {
        String[][] binRanges = new String[][]{};
        BINClassifier classifier = new BINClassifier(binRanges);

        String result = classifier.classify(card);

        assertThat("Empty or null card did not return null", result, is(nullValue()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"  ", "\t", "\n", "not a card", "1234567", "1 2 3 4 5 6 7"})
    void classify__randomValuesCard_returnsNull(String card) {
        String[][] binRanges = new String[][]{};
        BINClassifier classifier = new BINClassifier(binRanges);

        String result = classifier.classify(card);

        assertThat("Random values for card did not return null", result, is(nullValue()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"4111 1111 1111 1111", "4111 2222 2222 2222"})
    void classify_withValidRandomVisaCard_returnsVisa(String visaCard) {

        String[] visaBINs = new String[] {"4444 4444 11", "4444 4444 44", "Visa"};
        String[][] binRanges = new String[][] { visaBINs };
        String expected = "Visa";
        BINClassifier classifier = new BINClassifier(binRanges);

        String result = classifier.classify(visaCard);

        assertThat("Visa card classification failed", result, is(equalTo(expected)));
    }
}