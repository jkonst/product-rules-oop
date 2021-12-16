package rules;

import jkonst.kin.karta.assignment.data.TestData;
import jkonst.kin.karta.assignment.model.Item;
import jkonst.kin.karta.assignment.rules.TotalDiscountRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TotalDiscountRuleTest {
    private List<Item> mockItems;

    @BeforeAll
    public void setUp() {
        this.mockItems = TestData.fetchMockItems();
    }

    @Test
    public void shouldBeValid() {
        TotalDiscountRule rule = new TotalDiscountRule(new BigDecimal(0.9),
                total -> total.compareTo(new BigDecimal(75).setScale(2, RoundingMode.HALF_EVEN)) > 0);
        Assertions.assertTrue(rule.isValid(this.mockItems));
    }

    @Test
    public void shouldBeInvalid() {
        TotalDiscountRule rule = new TotalDiscountRule(new BigDecimal(0.9),
                total -> total.compareTo(new BigDecimal(750).setScale(2, RoundingMode.HALF_EVEN)) > 0);
        Assertions.assertFalse(rule.isValid(this.mockItems));
    }

    @Test
    public void shouldModifyAllItemsPrices() {
        TotalDiscountRule rule = new TotalDiscountRule(new BigDecimal(0.9),
                total -> total.compareTo(new BigDecimal(75).setScale(2, RoundingMode.HALF_EVEN)) > 0);
        BigDecimal preTotal = this.mockItems.stream().map(Item::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        rule.apply(this.mockItems);
        BigDecimal postTotal = this.mockItems.stream().map(Item::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        Assertions.assertTrue(preTotal.multiply(new BigDecimal(0.9)).setScale(2, RoundingMode.CEILING)
                .compareTo(postTotal) == 0);
    }

}
