package org.example.service;

import org.example.model.Cheque;
import org.example.model.User;
import org.example.repos.ChequeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChequeService {
    @Autowired
    ChequeRepository chequeRepository;

    public List<Cheque> getAllChequesUser(User user) {
        return chequeRepository.findByUserId(user.getId());
    }
}
