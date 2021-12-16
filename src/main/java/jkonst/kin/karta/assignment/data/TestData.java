package jkonst.kin.karta.assignment.data;

import jkonst.kin.karta.assignment.model.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class TestData {

    public static List<Item> fetchMockItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("0001", "Water Bottle", new BigDecimal(24.95).setScale(2, RoundingMode.HALF_EVEN)));
        items.add(new Item("0001", "Water Bottle", new BigDecimal(24.95).setScale(2, RoundingMode.HALF_EVEN)));
        items.add(new Item("0002", "Hoodle", new BigDecimal(65.00).setScale(2, RoundingMode.HALF_EVEN)));
        items.add(new Item("0003", "Sticker Set", new BigDecimal(3.99).setScale(2, RoundingMode.HALF_EVEN)));

        return items;
    }

}
