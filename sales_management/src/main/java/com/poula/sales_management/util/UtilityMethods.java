package com.poula.sales_management.util;

import java.math.BigInteger;

public class UtilityMethods {

    private final static BigInteger CENTS_MOD = new BigInteger("100");
    public static String bigIntegerToString(BigInteger price){

        BigInteger cents = price.mod(CENTS_MOD);
        return "";
    }
}
