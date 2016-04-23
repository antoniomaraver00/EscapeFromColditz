package com.jakemarsden.colditz.logging.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase;

import static ch.qos.logback.core.pattern.color.ANSIConstants.*;

/**
 * Colors the result of the child converter based on the {@link Level} of the given logging event.
 *
 * @author jakemarsden
 * @see ch.qos.logback.classic.pattern.color.HighlightingCompositeConverter
 */
public class LevelBasedForegroundCompositeConverter extends ForegroundCompositeConverterBase<ILoggingEvent> {

    @Override
    protected String getForegroundColorCode(ILoggingEvent event) {
        final Level level = event.getLevel();
        switch (level.toInt()) {
            case Level.ERROR_INT:
                return bold(RED_FG);

            case Level.WARN_INT:
                return bold(YELLOW_FG);

            case Level.INFO_INT:
                return bold(BLUE_FG);

            case Level.DEBUG_INT:
                return bold(DEFAULT_FG);

            case Level.TRACE_INT:
                return DEFAULT_FG;

            default:
                throw new IllegalArgumentException("Unknown level: " + level);
        }
    }

    private static String bold(String color) {
        // It is important for BOLD to be prepended - don't append it!
        return BOLD + color;
    }
}
