package jkonst.kin.karta.assignment.rules;

import jkonst.kin.karta.assignment.model.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.Predicate;

public class TotalDiscountRule implements Rule{
    private BigDecimal weight;
    private Predicate<BigDecimal> predicate;

    public TotalDiscountRule(BigDecimal weight, Predicate<BigDecimal> predicate) {
        this.weight = weight;
        this.predicate = predicate;
    }

    @Override
    public void apply(List<Item> items) {
        for (Item item : items) {
            item.setPrice(item.getPrice().multiply(this.weight).setScale(2, RoundingMode.HALF_EVEN));
        }
    }

    @Override
    public boolean isValid(List<Item> items) {
        BigDecimal total = items.stream().map(Item::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        return predicate.test(total);
    }
}
