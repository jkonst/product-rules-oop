package jkonst.kin.karta.assignment.data;

import jkonst.kin.karta.assignment.model.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Store {
    private final List<Item> fetchMockItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("0001", "Water Bottle", new BigDecimal(24.95).setScale(2, RoundingMode.HALF_EVEN)));
        items.add(new Item("0002", "Hoodle", new BigDecimal(65.00).setScale(2, RoundingMode.HALF_EVEN)));
        items.add(new Item("0003", "Sticker Set", new BigDecimal(3.99).setScale(2, RoundingMode.HALF_EVEN)));
        return items;
    }

    private final Map<String, Item> fetchItemsMap() {
        Map<String, Item> itemsMap = this.fetchMockItems().stream()
                .collect(Collectors.toMap(Item::getId, Function.identity()));
        return itemsMap;
    }

    public List<Item> getItems(String[] ids) {
        List<Item> items = new ArrayList<>();
        Map<String, Item> itemsMap = this.fetchItemsMap();
        for(String id: ids) {
            items.add(new Item(itemsMap.get(id)));
        }
        return items;
    }

}
