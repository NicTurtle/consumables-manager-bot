package com.github.nicturtle.model;

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

    public static String getMaterialsAdditionMenuMessage () {
        return  "В наличии:" +
                "\nВоска - " + waxQuantity + " мг" + " addWax" +
                "\nАрома масел - " + aromaOilQuantity + " мг" +
                "\n + Черное море - " + blackSeaAromaOilQuantity + " мг" + " /addBSOil" +
                "\n + Манго - " + mangoAromaOilQuantity + " мг" + " /addMangoOil" +
                "\n + Лемон - " + lemonAromaOilQuantity + " мг" + " /addLemonOil" +
                "\nФитилей - " + wickQuantity + " шт" +
                "\n + Нить - " + threadWickQuantity + " шт" + " /addThrWick" +
                "\n + Стабильный фитиль - " + stabilioWickQuantity + " шт" + " /addStabWick" +
                "\n + Деревянный - " + woodWickQuantity + " шт" + " /addWoodWick";

    }
    public static String getTotalQuantityOfStock() {
        String result = "В наличии:" +
                "\nВоска - " + waxQuantity + " мг" +
                "\nАрома масел - " + aromaOilQuantity + " мг" +
                "\n + Черное море - " + blackSeaAromaOilQuantity + " мг" +
                "\n + Манго - " + mangoAromaOilQuantity + " мг" +
                "\n + Лемон - " + lemonAromaOilQuantity + " мг" +
                "\nФитилей - " + wickQuantity + " шт" +
                "\n + Нить - " + threadWickQuantity + " шт" +
                "\n + Стабильный фитиль - " + stabilioWickQuantity + " шт" +
                "\n + Деревянный - " + woodWickQuantity + " шт";

        return result;
    }
}
/**
 *
В наличии:
Воска -
Арома масел -
 + Черное море -
 + Манго -
 + Лемон -
Фитилей -
 + Нить -
 + Стабильный фитиль -
 + Деревянный -

 */