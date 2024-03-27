package com.github.nicturtle.model;

public class MaterialStocks {

    /** wax */
    int waxQuantity = 23423;

    /** glass */
    int glass350mlQuantity = 100;
    int glass250mlQuantity = 50;
    int glass180mlQuantity = 9;
    int glass150mlQuantity = 51;
    int glassQuantity = glass150mlQuantity + glass180mlQuantity + glass250mlQuantity + glass350mlQuantity;

    /** aroma oil */
    int blackSeaAromaOilQuantity = 434;
    int mangoAromaOilQuantity = 23;
    int lemonAromaOilQuantity = 123;
    int aromaOilQuantity = blackSeaAromaOilQuantity + mangoAromaOilQuantity + lemonAromaOilQuantity;

    /** wicks */
    int threadWickQuantity = 5;
    int stabilioWickQuantity = 20;
    int woodWickQuantity = 30;

    int wickQuantity = threadWickQuantity + stabilioWickQuantity + woodWickQuantity;

    public String addMaterialsMenuMessage () {
        return  "В наличии:" +
                "\nВоска - " + waxQuantity + " мг" + " /addWax" +
                "\nАрома масел - " + aromaOilQuantity + " мг" +
                "\n + Черное море - " + blackSeaAromaOilQuantity + " мг" + " /+ЧерноеМоре" +
                "\n + Манго - " + mangoAromaOilQuantity + " мг" + " /+Манго" +
                "\n + Лемон - " + lemonAromaOilQuantity + " мг" + " /+Лемон" +
                "\nФитилей - " + wickQuantity + " шт" +
                "\n + Нить - " + threadWickQuantity + " шт" + " /+Нить" +
                "\n + Стабильный фитиль - " + stabilioWickQuantity + " шт" + " /+СтабильныйФитиль" +
                "\n + Деревянный - " + woodWickQuantity + " шт" + " /sold";

    }
    public String getTotalQuantityOfStock() {
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