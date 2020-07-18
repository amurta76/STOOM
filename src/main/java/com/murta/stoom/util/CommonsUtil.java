package com.murta.stoom.util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

public class CommonsUtil {

    /**
     * Verifica se a string é nula ou vazia.
     *
     * @param value
     * @return
     */
    public static final boolean semValor(String value) {
        return value == null || value.trim().isEmpty();
    }

    /**
     * Verifica se uma coleção é nula ou está vazia.
     *
     * @param value
     * @return
     */
    public static final boolean semValor(Collection<?> value) {
        return value == null || value.isEmpty();
    }

    /**
     * Verifica se o double é nula ou vazia.
     *
     * @param value
     * @return
     */
    public static final boolean semValor(Double value) {
        return value == null || value.doubleValue() == 0d;
    }

    /**
     * Verifica se o double é nula ou vazia.
     *
     * @param value
     * @return
     */
    public static final boolean semValor(BigDecimal value) {
        return value == null || value.doubleValue() == 0d;
    }

    public static final boolean semValor(Map<?, ?> value) {
        return value == null || value.isEmpty();
    }

}
