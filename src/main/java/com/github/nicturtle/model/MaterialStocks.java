package com.github.nicturtle.model;

//TODO: add List + SQL
public class MaterialStocks {

    /** wax */
    public static int waxQuantity = 23423;

    /** glass */
    public static int glass350mlQuantity = 100;
    public static int glass250mlQuantity = 50;
    public static int glass180mlQuantity = 9;
    public static int glass150mlQuantity = 51;
    public static int glassQuantity = glass150mlQuantity + glass180mlQuantity + glass250mlQuantity + glass350mlQuantity;

    /** aroma oil */
    public static int blackSeaAromaOilQuantity = 434;
    public static int mangoAromaOilQuantity = 23;
    public static int lemonAromaOilQuantity = 123;
    public static int aromaOilQuantity = blackSeaAromaOilQuantity + mangoAromaOilQuantity + lemonAromaOilQuantity;

    /** wicks */
    public static int threadWickQuantity = 5;
    public static int stabilioWickQuantity = 20;
    public static int woodWickQuantity = 30;

    public static int wickQuantity = threadWickQuantity + stabilioWickQuantity + woodWickQuantity;

}