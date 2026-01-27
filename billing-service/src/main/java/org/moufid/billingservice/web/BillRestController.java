package org.moufid.billingservice.web;

import org.moufid.billingservice.entities.Bill;
import org.moufid.billingservice.feign.CustomerRestClient;
import org.moufid.billingservice.feign.ProductRestClient;
import org.moufid.billingservice.repository.BillRepository;
import org.moufid.billingservice.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BillRestController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerRestClient customerRestClient;
    @Autowired
    private ProductRestClient productRestClient;
    @GetMapping(path = "/bills/{id}")
    public Bill getBill(@PathVariable Long id){
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(productRestClient.getProductById(productItem.getProductId()));
        });
        return bill;
    }

    @GetMapping(path = "/bills/search/byCustomerId")
    public List<Bill> billsByCustomer(@RequestParam("customerId") long customerId){
        List<Bill> bills = billRepository.findByCustomerId(customerId);
        bills.forEach(bill -> {
            bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerId()));
            bill.getProductItems().forEach(productItem -> {
                productItem.setProduct(productRestClient.getProductById(productItem.getProductId()));
            });
        });
        return bills;
    }
}
