package jkonst.kin.karta.assignment.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class Item {

    private String id;
    private String name;
    private BigDecimal price;

    public Item(String id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Item (Item itemToClone) {
        this(itemToClone.getId(), itemToClone.getName(), itemToClone.getPrice());
    }
}
