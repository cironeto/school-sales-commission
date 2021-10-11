package dev.cironeto.service;

import dev.cironeto.domain.Seller;
import dev.cironeto.repository.SellerRepository;

import java.util.List;
import java.util.Scanner;

public class SellerService {

    private static Scanner input = new Scanner(System.in);


    public static void buildMenu(int op){
        switch (op){
            case 1:
                findByName();
                break;
            case 2:
                delete();
                break;

            case 0:
                break;
            default:
                System.out.println("Invalid option");
        }

    }

    public static List<Seller> findByName(){
        System.out.println("Type the seller name or empty to search all");
        String name = input.nextLine();
        SellerRepository.findByName(name);
        return null;
    }

    public static void delete(){
        System.out.println("Type the ID you want to delete");
        Integer id = input.nextInt();
        SellerRepository.delete(id);

    }

}
