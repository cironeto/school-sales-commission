package dev.cironeto.service;

import dev.cironeto.domain.Seller;
import dev.cironeto.domain.Student;
import dev.cironeto.repository.SellerRepository;

import java.util.List;
import java.util.Scanner;

public class SellerService {

    private static Scanner input = new Scanner(System.in);


    public static void buildMenu(int op) {
        switch (op) {
            case 1:
                findByName();
                break;
            case 2:
                findById();
                break;
            case 3:
                delete();
                break;
            case 4:
                save();
                break;
            case 5:
                update();
                break;
            case 6:
                filterSalesById();
                break;
            case 7:
                showTotalCommissionById();
                break;
            case 9:
                break;
            default:
                System.out.println("Invalid option");
        }

    }

    public static List<Seller> findByName() {
        System.out.println("Type the seller name or empty to search all");
        SellerRepository.findByName(input.nextLine());
        return null;
    }

    public static Seller findById() {
        System.out.println("Type the seller ID or empty to search all");
        SellerRepository.findById(input.nextInt());
        return null;
    }

    public static void delete() {
        System.out.println("Type the ID you want to delete");
        Integer id = input.nextInt();
        SellerRepository.delete(id);
        System.out.println(String.format("Seller ID %s removed from database", id));
    }

    public static void save() {
        System.out.println("Type the seller's name");
        String name = input.nextLine();
        System.out.println("type the seller's salary");
        Double salary = input.nextDouble();
        Seller seller = Seller.SellerBuilder.seller()
                .name(name)
                .salary(salary)
                .build();
        SellerRepository.save(seller);
        System.out.println(String.format("Seller %s saved with success", seller.getName()));
    }

    public static void update() {
        System.out.println("Type the ID you want to update");
        Seller seller = SellerRepository.findById(input.nextInt());
        System.out.println("Type the new name");
        String nameToUpdate = input.next();
        System.out.println("Type the new salary");
        double salaryToUpdate = input.nextDouble();

        Seller sellerToUpdate = Seller.SellerBuilder.seller()
                .name(nameToUpdate)
                .salary(salaryToUpdate)
                .id(seller.getId())
                .build();
        SellerRepository.update(sellerToUpdate);
    }

    public static List<Student> filterSalesById() {
        System.out.println("Type the ID you want to filter");
        SellerRepository.filterSalesById(input.nextInt());
        return null;
    }

    public static double showTotalCommissionById() {
        System.out.println("Type the ID you want to see the commission");
        SellerRepository.showTotalCommissionById(input.nextInt());
        return 0;
    }


}
