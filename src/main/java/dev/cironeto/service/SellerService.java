package dev.cironeto.service;

import dev.cironeto.domain.Seller;
import dev.cironeto.repository.SellerRepository;

import java.util.List;

public class SellerService {

    public static List<Seller> findByName(String name){
        return SellerRepository.findByName(name);
    }
}
