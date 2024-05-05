package org.example.service;

import org.example.model.Cheque;
import org.example.model.PurchasedProduct;
import org.example.model.User;
import org.example.repos.ChequeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChequeService {
    @Autowired
    ChequeRepository chequeRepository;

    public ArrayList<Cheque> getAllChequesUser(User user) {
        return (ArrayList<Cheque>)chequeRepository.findByUserId(user.getId());
    }

    public void saveOrder(User user) {
        Cheque cheque = new Cheque(user.getId(), user.getProducts(), user.getSum());
        chequeRepository.save(cheque);
    }
}