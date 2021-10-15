package Test;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import dev.cironeto.domain.Seller;
import dev.cironeto.domain.Student;
import dev.cironeto.repository.SellerRepository;
import dev.cironeto.repository.StudentRepository;
import dev.cironeto.service.StudentService;
import lombok.extern.log4j.Log4j2;
import dev.cironeto.service.SellerService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@Log4j2
public class Test {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int op;
        do {
            mainMenu();
            op = input.nextInt();
            switch (op) {
                case 1:
                    buildStudentMenu();
                    op = input.nextInt();
                    StudentService.buildMenu(op);
                    break;
                case 2:
                    buildSellerMenu();
                    op = input.nextInt();
                    SellerService.buildMenu(op);
                    break;
                default:
                    System.out.println("invalid option");
            }
        }
        while (op != 0);

    }

        private static void mainMenu () {
            System.out.println("----------------------------------");
            System.out.println("CHOOSE AN OPTION TYPING THE NUMBER");
            System.out.println("1 - STUDENT menu");
            System.out.println("2 - SELLER menu");
            System.out.println("0 - Exit");
            System.out.println("----------------------------------");
        }

        private static void buildStudentMenu () {
            System.out.println("----------------------------------");
            System.out.println("CHOOSE AN OPTION TYPING THE NUMBER");
            System.out.println("1 - Search student(s)");
            System.out.println("2 - Delete student");
            System.out.println("3 - Insert student");
            System.out.println("4 - Update student");
            System.out.println("9 - Return to main menu");
            System.out.println("----------------------------------");
        }

        private static void buildSellerMenu () {
            System.out.println("----------------------------------");
            System.out.println("CHOOSE AN OPTION TYPING THE NUMBER");
            System.out.println("1 - Search seller(s)");
            System.out.println("2 - Delete seller");
            System.out.println("3 - Insert seller");
            System.out.println("4 - Update seller");
            System.out.println("5 - Filter sales by ID");
            System.out.println("9 - Return to main menu");
            System.out.println("----------------------------------");
        }

    }

