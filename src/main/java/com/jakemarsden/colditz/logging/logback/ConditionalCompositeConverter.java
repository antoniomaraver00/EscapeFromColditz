package com.jakemarsden.colditz.logging.logback;

import ch.qos.logback.core.pattern.CompositeConverter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

import static ch.qos.logback.core.CoreConstants.EMPTY_STRING;

/**
 * A Logback converter which only returns the result of its child converter for events which
 * satisfy a specified {@link Predicate}.
 *
 * @author jakemarsden
 */
@RequiredArgsConstructor
public class ConditionalCompositeConverter<E> extends CompositeConverter<E> {
    @NonNull
    private final Predicate<E> condition;


    @Override
    protected String transform(E event, String in) {
        return condition.test(event) ? in : EMPTY_STRING;
    }
}
