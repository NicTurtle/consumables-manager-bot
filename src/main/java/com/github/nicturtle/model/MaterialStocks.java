package com.github.nicturtle.model;

//TODO: add List + SQL
public class MaterialStocks {

    /** wax */
    public static int waxQuantity = 0;

    /** glass */
    public static int glass350mlQuantity = 0;
    public static int glass250mlQuantity = 0;
    public static int glass180mlQuantity = 0;
    public static int glass150mlQuantity = 0;
    public static int glassQuantity = glass150mlQuantity + glass180mlQuantity + glass250mlQuantity + glass350mlQuantity;

    /** aroma oil */
    public static int blackSeaAromaOilQuantity = 0;
    public static int mangoAromaOilQuantity = 0;
    public static int lemonAromaOilQuantity = 0;
    public static int aromaOilQuantity = blackSeaAromaOilQuantity + mangoAromaOilQuantity + lemonAromaOilQuantity;

    /** wicks */
    public static int threadWickQuantity = 0;
    public static int stabilioWickQuantity = 0;
    public static int woodWickQuantity = 0;

    public static int wickQuantity = threadWickQuantity + stabilioWickQuantity + woodWickQuantity;

}