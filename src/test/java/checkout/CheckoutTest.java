package checkout;

import jkonst.kin.karta.assignment.checkout.Checkout;
import jkonst.kin.karta.assignment.data.TestData;
import jkonst.kin.karta.assignment.model.Item;
import jkonst.kin.karta.assignment.rules.Rule;
import jkonst.kin.karta.assignment.rules.SingleItemRule;
import jkonst.kin.karta.assignment.rules.TotalDiscountRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CheckoutTest {
    private List<Item> mockItems;

    @BeforeAll
    public void setUp() {
        this.mockItems = TestData.fetchMockItems();
    }

    @Test
    public void shouldPrintTheExpectedTotal() {
        List<Rule> rules = new ArrayList<>();
        Rule waterRule = new SingleItemRule(new BigDecimal(0.9215).setScale(4, RoundingMode.HALF_EVEN),
                item -> item.getId().equals("0001"),
                items -> items.size() >= 2);
        rules.add(waterRule);
        Rule totalDiscountRule = new TotalDiscountRule(new BigDecimal(0.9),
                total -> total.compareTo(new BigDecimal(75).setScale(2, RoundingMode.HALF_EVEN)) > 0);
        rules.add(totalDiscountRule);
        Checkout checkout = new Checkout(rules);
        checkout.scan(this.mockItems);
        BigDecimal total = this.mockItems.stream().map(Item::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        Assertions.assertTrue(total.compareTo(new BigDecimal(103.47)
                .setScale(2, RoundingMode.HALF_EVEN)) == 0);
    }

}
