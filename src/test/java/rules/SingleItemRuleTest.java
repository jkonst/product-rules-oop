package rules;

import jkonst.kin.karta.assignment.model.Item;
import jkonst.kin.karta.assignment.rules.SingleItemRule;
import jkonst.kin.karta.assignment.data.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SingleItemRuleTest {

    private List<Item> mockItems;

    @BeforeAll
    public void setUp() {
        this.mockItems = TestData.fetchMockItems();
    }

    @Test
    public void shouldBeValid() {
        SingleItemRule rule = new SingleItemRule(new BigDecimal(0.9215).setScale(4, RoundingMode.HALF_EVEN),
                item -> item.getId().equals("0001"),
                items -> items.size() >= 2);
        Assertions.assertTrue(rule.isValid(this.mockItems));
    }

    @Test
    public void shouldBeInvalid() {
        SingleItemRule rule = new SingleItemRule(new BigDecimal(0.9215).setScale(4, RoundingMode.HALF_EVEN),
                item -> item.getId().equals("0001"),
                items -> items.size() >= 3);
        Assertions.assertFalse(rule.isValid(this.mockItems));
    }

    @Test
    public void shouldModifyTheItemPrice() {
        SingleItemRule rule = new SingleItemRule(new BigDecimal(0.9215).setScale(4, RoundingMode.HALF_EVEN),
                item -> item.getId().equals("0001"),
                items -> items.size() >= 2);
        rule.apply(this.mockItems);
        List<Item> affectedItems = this.mockItems.stream().filter(item -> item.getId().equals("0001")).collect(Collectors.toList());
        boolean shouldHaveModifiedPricesAccordingly = affectedItems
                .stream()
                .map(item -> item.getPrice())
                .allMatch(p -> p.compareTo(new BigDecimal(22.99).setScale(2, RoundingMode.HALF_EVEN)) == 0);
        Assertions.assertTrue(shouldHaveModifiedPricesAccordingly);
    }

    @Test
    public void shouldNotModifyTheItemPrice() {
        SingleItemRule rule = new SingleItemRule(new BigDecimal(0.9215).setScale(4, RoundingMode.HALF_EVEN),
                item -> item.getId().equals("0004"),
                items -> items.size() >= 2);
        rule.apply(this.mockItems);
        List<Item> affectedItems = this.mockItems.stream().filter(item -> item.getId().equals("0004")).collect(Collectors.toList());
        Assertions.assertTrue(affectedItems.isEmpty());
    }


}
