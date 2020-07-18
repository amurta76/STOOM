package util;

import java.util.Collection;

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

}
