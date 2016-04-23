package com.jakemarsden.colditz.logging.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxy;
import ch.qos.logback.core.pattern.CompositeConverter;
import ch.qos.logback.core.pattern.Converter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jakemarsden
 */
public class HasThrowableConditionalConverterTest {
    private static final String EXPECTED_RESULT_POSITIVE = "Positive Test Result";
    private static final String EXPECTED_RESULT_NEGATIVE = "";


    private Converter<ILoggingEvent> initClassUnderTest() {
        CompositeConverter<ILoggingEvent> converter = new HasThrowableConditionalConverter();
        converter.setChildConverter(new MockConverter<>(EXPECTED_RESULT_POSITIVE));
        return converter;
    }


    @Test
    public void testTransformPositive() {
        final Converter<ILoggingEvent> classUnderTest = initClassUnderTest();
        final LoggingEvent testEvent = new LoggingEvent();
        testEvent.setThrowableProxy(new ThrowableProxy(new Exception("Mock Exception")));

        final String result = classUnderTest.convert(testEvent);
        assertEquals(EXPECTED_RESULT_POSITIVE, result);
    }

    @Test
    public void testTransformNegative() {
        final Converter<ILoggingEvent> classUnderTest = initClassUnderTest();
        final ILoggingEvent testEvent = new LoggingEvent();

        final String result = classUnderTest.convert(new LoggingEvent());
        assertEquals(EXPECTED_RESULT_NEGATIVE, result);
    }
}
