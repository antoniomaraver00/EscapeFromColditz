package com.jakemarsden.colditz.logging.logback;

import ch.qos.logback.core.pattern.Converter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * A Logback converter which returns a pre-computed value for its {@link #convert(Object)}
 * implementation.
 *
 * @author jakemarsden
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class MockConverter<E> extends Converter<E> {
    @Getter
    private final String conversionResult;


    @Override
    public String convert(E event) {
        return conversionResult;
    }
}
