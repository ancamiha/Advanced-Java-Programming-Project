package com.api.sweetshop.controller;

import java.util.List;

import com.api.sweetshop.model.Cart;
import com.api.sweetshop.model.Sweets;
import com.api.sweetshop.pojo.ProdOfCart;
import com.api.sweetshop.repository.CartRepository;
import com.api.sweetshop.repository.SweetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SweetShop")
public class CartController {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    SweetsRepository sweetsRepository;

    @GetMapping(value = { "/cart/{id}" },
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Sweets> getCart(@PathVariable Long id) {
        List<Long> sweetsIds = this.cartRepository.getByUserId(id).getProducts();
        List<Sweets> sweets = this.sweetsRepository.findAllById(sweetsIds);
        return sweets;
    }

    @PostMapping(value = { "/addProdToCart" }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addProdToCart(@RequestBody ProdOfCart prodOfCart) {
        System.out.println(prodOfCart);
        if (cartRepository.existsByUserId(prodOfCart.getUserId())) {
            Cart cart = cartRepository.getByUserId(prodOfCart.getUserId());
            if (!cart.addProductId(prodOfCart.getProduct()))
                return ResponseEntity.ok("Already exists");
            else {
                cartRepository.save(cart);
                return ResponseEntity.ok("Success");
            }
        } else {
            List<Long> products = List.of(prodOfCart.getProduct());
            cartRepository.save(new Cart(prodOfCart.getUserId(), products));
            return ResponseEntity.ok("Cart created and product added");
        }
    }

    @DeleteMapping(value = { "/deleteProdFromCart" }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteProdFromCart(@RequestBody ProdOfCart prodOfCart) {
        if (cartRepository.existsByUserId(prodOfCart.getUserId())) {
            Cart cart = cartRepository.getByUserId(prodOfCart.getUserId());
            cart.getProducts().remove(prodOfCart.getProduct());
            cartRepository.save(cart);
            return ResponseEntity.ok("Product deleted");
        } else {
            return ResponseEntity.ok("Cart not found");
        }
    }
}
