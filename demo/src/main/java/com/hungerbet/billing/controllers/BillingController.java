package com.hungerbet.billing.controllers;

import com.hungerbet.billing.controllers.Requests.DebitingMoneyRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/billing-api")
public class BillingController {

    private static HashMap<String, Double> billings = new HashMap<>();

    @PostMapping
    public ResponseEntity DebitingMoney(@RequestBody DebitingMoneyRequest request) {
        if(billings.containsKey(request.getLogin())){
            double newAmount = billings.get(request.getLogin()) - request.getAmount();
            if(newAmount < 0 ){
                return ResponseEntity.badRequest().body("Not enough money");
            }

            billings.put(request.getLogin(), newAmount);
            return ResponseEntity.noContent().build();
        }

        billings.put(request.getLogin(), 10000d);
        return ResponseEntity.noContent().build();
    }
}
