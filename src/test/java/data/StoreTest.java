package data;

import jkonst.kin.karta.assignment.data.Store;
import jkonst.kin.karta.assignment.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class StoreTest {

    @Test
    public void shouldGetTheExpectedItemsList() {
        Store store = new Store();
        List<Item> fetchedItems = store.getItems(new String[]{"0001", "0001", "0002", "0003"});
        Assertions.assertEquals(4, fetchedItems.size());
        BigDecimal total = fetchedItems.stream().map(Item::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        Assertions.assertTrue(total.compareTo(new BigDecimal(118.89)
                .setScale(2, RoundingMode.HALF_EVEN)) == 0);
    }
}
