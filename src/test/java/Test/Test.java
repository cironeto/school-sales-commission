package Test;

import dev.cironeto.domain.Seller;
import lombok.extern.log4j.Log4j2;
import dev.cironeto.service.SellerService;

import java.util.List;
import java.util.Scanner;

@Log4j2
public class Test {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int op;

        while (true) {
            buildMainMenu();
            op = Integer.parseInt(input.nextLine());
            SellerService.buildMenu(op);
            if(op == 0) break;
        }
    }

        private static void buildMainMenu() {
            System.out.println("----------------------------------");
            System.out.println("CHOOSE AN OPTION TYPING THE NUMBER");
            System.out.println("1 - Search seller");
            System.out.println("2 - Delete seller");
            System.out.println("0 - Exit");
            System.out.println("----------------------------------");
        }

    }

