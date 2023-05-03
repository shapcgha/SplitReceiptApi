package com.shapca.splitaccountapi.service;

import com.shapca.splitaccountapi.domain.Product;
import com.shapca.splitaccountapi.domain.Receipt;
import com.shapca.splitaccountapi.domain.User;
import com.shapca.splitaccountapi.form.ProductData;
import com.shapca.splitaccountapi.repository.ReceiptRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final UserService userService;

    public ReceiptService(ReceiptRepository receiptRepository, UserService userService) {
        this.receiptRepository = receiptRepository;
        this.userService = userService;
    }

    public Receipt addReceipt(User user, String name) {
        Receipt receipt = new Receipt();
        receipt.setName(name);
        receipt.setOwnerId(user.getId());
        receipt.setUsers(new HashSet<>());
        receipt.getUsers().add(user);
        user.getReceipts().add(receipt);
        return receiptRepository.save(receipt);
    }

    public Product addProduct(Receipt receipt, ProductData productData, User user) {
        List<Product> sameProducts = receipt.getProducts().stream().filter(it -> !it.isOwn() && it.getName().equals(productData.getName())).toList();
        Product product;
        if (sameProducts.isEmpty() || productData.isOwn()) {
            product = new Product();
            product.setPrice(productData.getPrice());
            product.setName(productData.getName());
            product.setOwners(new HashSet<>());
            product.setCount(product.getCount());
            product.setOwn(productData.isOwn());
            product.setReceipt(receipt);
            receipt.getProducts().add(product);
        } else {
            product = sameProducts.get(0);
        }
        product.getOwners().add(user);
        user.getProducts().add(product);
        receiptRepository.save(receipt);
        return product;
    }

    public Receipt addUser(Receipt receipt, long userId) {
        User user = userService.findById(userId);
        receipt.getUsers().add(user);
        user.getReceipts().add(receipt);
        receiptRepository.save(receipt);
        return receipt;
    }

    public double userPrice(Receipt receipt, User user) {
        double price = 0;
        for (Product product : receipt.getProducts()) {
            if (product.getOwners().contains(user)) {
                price += product.getPrice() / (double) product.getOwners().size() * product.getCount();
            }
        }
        return price;
    }

    public Receipt findById(long receiptId) {
        return receiptRepository.findById(receiptId).orElse(null);
    }
}
