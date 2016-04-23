package com.jakemarsden.colditz.logging.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * A Logback converter which only returns the result of its child converter for events which
 * contain a non-{@code null} {@link ch.qos.logback.classic.spi.IThrowableProxy}.
 * <p>
 * One use case would be adding extra line breaks to the end of logged messages containing a
 * stacktrace: {@code %message%n%hasException(%n%n))} (where {@code hasException} has
 * been setup to reference this class).
 *
 * @author jakemarsden
 * @see ch.qos.logback.classic.pattern.NopThrowableInformationConverter
 */
public class HasThrowableConditionalConverter extends ConditionalCompositeConverter<ILoggingEvent> {

    public HasThrowableConditionalConverter() {
        super(event -> event.getThrowableProxy() != null);
    }
}
