package jkonst.kin.karta.assignment;

import jkonst.kin.karta.assignment.checkout.Checkout;
import jkonst.kin.karta.assignment.model.Item;
import jkonst.kin.karta.assignment.rules.Rule;
import jkonst.kin.karta.assignment.rules.SingleItemRule;
import jkonst.kin.karta.assignment.rules.TotalDiscountRule;
import jkonst.kin.karta.assignment.data.Store;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] ids = {"0002", "0002", "0003"};
        Store store = new Store();
        List<Item> itemList = store.getItems(ids);
        List<Rule> rules = new ArrayList<>();
        Rule waterRule = new SingleItemRule(new BigDecimal(0.9215).setScale(4, RoundingMode.HALF_EVEN),
                item -> item.getId().equals("0001"),
                items -> items.size() >= 2);
        rules.add(waterRule);
        Rule totalDiscountRule = new TotalDiscountRule(new BigDecimal(0.9),
                total -> total.compareTo(new BigDecimal(75).setScale(2, RoundingMode.HALF_EVEN)) > 0);
        rules.add(totalDiscountRule);
        checkout(rules, itemList);
    }

    private static void checkout(List<Rule> rules, List<Item> items) {
        Checkout checkout = new Checkout(rules);
        checkout.scan(items);
        checkout.total();
    }
}
