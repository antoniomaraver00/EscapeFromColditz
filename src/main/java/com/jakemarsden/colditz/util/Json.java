package com.jakemarsden.colditz.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * Provides access the global Jackson {@link ObjectMapper} reference
 *
 * @author jakemarsden
 */
public final class Json {
    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper(new YAMLFactory());
    }


    /**
     * @return The global Jackson {@link ObjectMapper}
     */
    public static ObjectMapper getMapper() {
        return mapper;
    }


    private Json() {
        throw new UnsupportedOperationException();
    }
}
