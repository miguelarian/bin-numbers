package com.miguelvela;

import org.junit.jupiter.api.Test;
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
    void classify_withValidVisaCard_returnsVisa(String visaCard) {

        String[] visaBINs = new String[] {"4111 1111 11", "4444 4444 44", "Visa"};
        String[][] binRanges = new String[][] { visaBINs };
        String expected = "Visa";
        BINClassifier classifier = new BINClassifier(binRanges);

        String result = classifier.classify(visaCard);

        assertThat("Visa card classification failed", result, is(equalTo(expected)));
    }

    @Test
    void classify_withVisaCardAndNoVisaRange_returnsNull() {

        String[] masterCardBINs = new String[] {"5555 5555 55", "5555 9999 99", "Master Card"};
        String[][] binRanges = new String[][] { masterCardBINs };
        String visaCard = "4111 1111 1111 1111";
        String expected = null;
        BINClassifier classifier = new BINClassifier(binRanges);

        String result = classifier.classify(visaCard);

        assertThat("Visa card classification failed", result, is(equalTo(expected)));
    }

    @Test
    void classify_withCachedCard_resultIsRetrievedFromCache() {

        String[] masterCardBINs = new String[] {"5555 5555 55", "5555 9999 99", "Master Card"};
        String[][] binRanges = new String[][] { masterCardBINs };
        String masterCard = "5555 5555 5555 5555";
        BINClassifier classifier = new BINClassifier(binRanges);
        String expected = "Master Card";

        String result1 = classifier.classify(masterCard);
        assertThat("Master Card classification failed", result1, is(equalTo(expected)));
        String result2 = classifier.classify(masterCard);
        assertThat("Master Card classification cached failed", result2, is(equalTo(expected)));
    }
}