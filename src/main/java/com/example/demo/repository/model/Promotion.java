package com.example.demo.repository.model;

import java.util.List;
import java.util.Objects;

public class Promotion
{
    private int discountRate;
    private List<String> items;

    public Promotion(int discountRate,
                     List<String> items)
    {
        this.discountRate = discountRate;
        this.items = items;
    }

    public int getDiscountRate()
    {
        return discountRate;
    }

    public List<String> getItems()
    {
        return items;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Promotion promotion = (Promotion) o;
        return discountRate == promotion.discountRate && items.equals(promotion.items);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(discountRate, items);
    }

    @Override
    public String toString()
    {
        return "Promotion{" +
            "discountRate=" + discountRate +
            ", items=" + items +
            '}';
    }
}
