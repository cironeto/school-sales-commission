package dev.cironeto.service;

import dev.cironeto.domain.Seller;
import dev.cironeto.domain.Student;
import dev.cironeto.repository.SellerRepository;
import dev.cironeto.repository.StudentRepository;

import java.util.List;
import java.util.Scanner;

public class StudentService {

    private static Scanner input = new Scanner(System.in);


    public static void buildMenu(int op) {
        switch (op) {
            case 1:
                findByName();
                break;
            case 2:
                delete();
                break;
            case 3:
                save();
                break;
            case 4:
                update();
                break;
            case 9:
                break;
            default:
                System.out.println("Invalid option");
        }

    }

    public static List<Student> findByName() {
        System.out.println("Type the student name or empty to search all");
        StudentRepository.findByName(input.nextLine());
        return null;
    }

    public static Student findById() {
        System.out.println("Type the student ID or empty to search all");
        StudentRepository.findById(input.nextInt());
        return null;
    }

    public static void delete() {
        System.out.println("Type the ID you want to delete");
        Integer id = input.nextInt();
        StudentRepository.delete(id);
        System.out.println(String.format("Student ID %s removed from database", id));
    }

    public static void save() {
        System.out.println("Type the student's name");
        String name = input.next();
        System.out.println("type the student's course");
        String course = input.next();
        System.out.println("type the student's fee");
        Double fee = input.nextDouble();
        System.out.println("type the student's tuition");
        Double tuition = input.nextDouble();
        System.out.println("type the seller's ID");
        Integer sellerId = input.nextInt();
        Student student = Student.StudentBuilder.student()
                .name(name)
                .course(course)
                .fee(fee)
                .tuition(tuition)
                .seller(Seller.SellerBuilder.seller().id(sellerId).build())
                .build();
        StudentRepository.save(student);
        System.out.println(String.format("Student %s saved with success", student.getName()));
    }

    public static void update() {
        System.out.println("Type the ID you want to update");
        Student student = StudentRepository.findById(input.nextInt());
        System.out.println("Type the new course");
        String courseToUpdate = input.next();
        System.out.println("Type the new fee");
        Double feeToUpdate = input.nextDouble();
        System.out.println("Type the new tuition");
        Double tuitionToUpdate = input.nextDouble();
        Student studentToUpdate = Student.StudentBuilder.student()
                .course(courseToUpdate)
                .fee(feeToUpdate)
                .tuition(tuitionToUpdate)
                .id(student.getId())
                .build();
        StudentRepository.update(studentToUpdate);
    }


}
