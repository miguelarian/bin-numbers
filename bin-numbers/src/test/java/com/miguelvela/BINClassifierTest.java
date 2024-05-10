package com.miguelvela;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class BINClassifierTest {

    @Test
    void classify_withEmptyStringCard_returnsNull() {
        String[][] binRanges = new String[][]{};
        BINClassifier classifier = new BINClassifier(binRanges);

        String result = classifier.classify("");

        assertThat("Empty String did not return null", result, is(nullValue()));
    }

    @Test
    void classify__nullCard_returnsNull() {
        String[][] binRanges = new String[][]{};
        BINClassifier classifier = new BINClassifier(binRanges);

        String result = classifier.classify(null);

        assertThat("Null card did not return null", result, is(nullValue()));
    }

    @Test
    void classify_withRandomStringCard_returnsNull() {
        String[][] binRanges = new String[][]{};
        BINClassifier classifier = new BINClassifier(binRanges);

        String result = classifier.classify("this is not a card");

        assertThat("A random String did not return null", result, is(nullValue()));
    }
}