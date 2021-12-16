package jkonst.kin.karta.assignment.rules;

import jkonst.kin.karta.assignment.model.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SingleItemRule implements Rule{
    private BigDecimal weight;
    private Predicate<Item> singlePredicate;
    private Predicate<List<Item>> groupPredicate;


    public SingleItemRule(BigDecimal weight, Predicate<Item> singlePredicate, Predicate<List<Item>> groupPredicate) {
        this.weight = weight;
        this.singlePredicate = singlePredicate;
        this.groupPredicate = groupPredicate;
    }

    @Override
    public void apply(List<Item> items) {
        for (Item item : items) {
            if (this.singlePredicate.test(item)) {
                BigDecimal price = item.getPrice().multiply(weight).setScale(2, RoundingMode.HALF_EVEN);
                item.setPrice(price);
            }
        }
    }

    @Override
    public boolean isValid(List<Item> items) {
        List<Item> filteredItems = items.stream().filter(singlePredicate).collect(Collectors.toList());
        return groupPredicate.test(filteredItems);
    }
}
