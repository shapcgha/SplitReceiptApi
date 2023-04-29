package com.shapca.splitaccountapi.controller;

import com.shapca.splitaccountapi.domain.Product;
import com.shapca.splitaccountapi.domain.Receipt;
import com.shapca.splitaccountapi.domain.User;
import com.shapca.splitaccountapi.exception.ValidationException;
import com.shapca.splitaccountapi.form.ProductData;
import com.shapca.splitaccountapi.form.ReceiptData;
import com.shapca.splitaccountapi.service.ReceiptService;
import com.shapca.splitaccountapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/1/")
public class ReceiptController {

    private final ReceiptService receiptService;
    private final UserService userService;

    public ReceiptController(ReceiptService receiptService, UserService userService) {
        this.receiptService = receiptService;
        this.userService = userService;
    }

    @GetMapping("/receipts")
    @ResponseStatus(HttpStatus.OK)
    public Set<Receipt> getAllReceipts(@RequestParam String jwt) {
        return userService.findByJWT(jwt).getReceipts();
    }

    @PostMapping("/receipts")
    @ResponseStatus(HttpStatus.OK)
    public Receipt getAllReceipts(@RequestParam String jwt, @Valid @RequestBody ReceiptData data, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        return receiptService.addReceipt(userService.findByJWT(jwt), data.getName());
    }

    @GetMapping("/receipts/{id}/users")
    @ResponseStatus(HttpStatus.OK)
    public Set<User> getAllUsers(@PathVariable long id, @RequestParam String jwt) {
        return receiptService.findById(id).getUsers();
    }

    @PostMapping("/receipts/{id}/users")
    @ResponseStatus(HttpStatus.OK)
    public Receipt addUser(@PathVariable long id, @RequestParam String jwt, @RequestBody long userId) {
        return receiptService.addUser(receiptService.findById(id), userId);
    }

    @GetMapping("/receipts/{id}/products")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts(@PathVariable long id, @RequestParam String jwt) {
        return receiptService.findById(id).getProducts();
    }

    @PostMapping("/receipts/{id}/products")
    @ResponseStatus(HttpStatus.OK)
    public Product addProduct(@PathVariable long id, @RequestBody @Valid ProductData product, @RequestParam String jwt) {
        return receiptService.addProduct(receiptService.findById(id), product, userService.findByJWT(jwt));
    }

    @GetMapping("/receipts/{id}/price")
    @ResponseStatus(HttpStatus.OK)
    public double getPrice(@PathVariable long id, @RequestParam String jwt) {
        return receiptService.userPrice(receiptService.findById(id), userService.findByJWT(jwt));
    }
}
