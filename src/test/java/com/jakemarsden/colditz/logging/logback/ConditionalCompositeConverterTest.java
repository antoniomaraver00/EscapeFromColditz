package com.jakemarsden.colditz.logging.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.pattern.CompositeConverter;
import ch.qos.logback.core.pattern.Converter;
import org.junit.Test;

import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;

/**
 * @author jakemarsden
 */
public class ConditionalCompositeConverterTest {
    private static final String EXPECTED_RESULT_POSITIVE = "Positive Test Result";
    private static final String EXPECTED_RESULT_NEGATIVE = "";


    private <E> Converter<E> initClassUnderTest(Predicate<E> testCondition) {
        CompositeConverter<E> converter = new ConditionalCompositeConverter<>(testCondition);
        converter.setChildConverter(new MockConverter<>(EXPECTED_RESULT_POSITIVE));
        return converter;
    }


    @Test
    public void testTransformPositive() {
        final Converter<ILoggingEvent> classUnderTest = initClassUnderTest((event) -> true);
        final String result = classUnderTest.convert(new LoggingEvent());
        assertEquals(EXPECTED_RESULT_POSITIVE, result);
    }

    @Test
    public void testTransformNegative() {
        final Converter<ILoggingEvent> classUnderTest = initClassUnderTest((event) -> false);
        final String result = classUnderTest.convert(new LoggingEvent());
        assertEquals(EXPECTED_RESULT_NEGATIVE, result);
    }
}
