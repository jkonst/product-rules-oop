package jkonst.kin.karta.assignment.checkout;

import jkonst.kin.karta.assignment.model.Item;
import jkonst.kin.karta.assignment.rules.Rule;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
public class Checkout {
    private List<Rule> rules;
    private List<Item> items;

    public Checkout(List<Rule> rules) {
        this.rules = rules;
    }

    public void scan(List<Item> items) {
        this.items = items;
        for(Rule rule : rules) {
            if (rule.isValid(this.items)) {
                rule.apply(this.items);
            }
        }
    }

    public void total() {
        BigDecimal total = this.items.stream().map(Item::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total Price: £" + total.toString());
    }
}
