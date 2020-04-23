package com.tchristofferson;

public class QueryBuilderUtil {

    public static String getInString(Object[] objects) {
        StringBuilder builder = new StringBuilder("(");

        for (int i = 0; i < objects.length; i++) {
            Object object = objects[i];

            if (object instanceof CharSequence || object instanceof Character) {
                builder.append("'").append(object).append("'");
            } else {
                builder.append(object);
            }

            if (i != objects.length - 1) {
                builder.append(", ");
            }
        }

        return builder.append(")").toString();
    }

    public static String getInString(char[] chars) {
        return getInString(convertToObject(chars));
    }

    public static String getInString(int[] ints) {
        return getInString(convertToObject(ints));
    }

    public static String getInString(double[] doubles) {
        return getInString(convertToObject(doubles));
    }

    private static Character[] convertToObject(char[] characters) {
        Character[] chars = new Character[characters.length];

        for (int i = 0; i < characters.length; i++) {
            chars[i] = characters[i];
        }

        return chars;
    }

    private static Integer[] convertToObject(int[] integers) {
        Integer[] ints = new Integer[integers.length];

        for (int i = 0; i < integers.length; i++) {
            ints[i] = integers[i];
        }

        return ints;
    }

    private static Double[] convertToObject(double[] doubles) {
        Double[] ds = new Double[doubles.length];

        for (int i = 0; i < doubles.length; i++) {
            ds[i] = doubles[i];
        }

        return ds;
    }
}
