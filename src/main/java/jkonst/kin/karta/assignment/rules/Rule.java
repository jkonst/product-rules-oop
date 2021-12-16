package jkonst.kin.karta.assignment.rules;

import jkonst.kin.karta.assignment.model.Item;

import java.util.List;

public interface Rule {
    void apply(List<Item> items);
    boolean isValid(List<Item> items);
}
