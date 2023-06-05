package com.api.sweetshop.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
    @SequenceGenerator(name = "cart_seq", sequenceName = "cart_seq", allocationSize = 1)
    private Long id;
    private Long userId;
    @ElementCollection
    private List<Long> products;

    public Cart() {
    }

    public Cart(Long userId, List<Long> products) {
        this.userId = userId;
        this.products = products;
    }

    public boolean addProductId(Long productId) {
        if (products.contains(productId))
            return false;
        products.add(productId);
        return true;
    }
}
