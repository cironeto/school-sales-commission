package dev.cironeto.domain;

import java.util.Objects;

public class Student {
    private Integer id;
    private String name;
    private String course;
    private Double fee;
    private Double tuition;
    private Seller seller;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public Double getFee() {
        return fee;
    }

    public Double getTuition() {
        return tuition;
    }

    public Seller getSeller() {
        return seller;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", course='" + course + '\'' +
                ", fee=" + fee +
                ", tuition=" + tuition +
                ", seller=" + seller +
                '}';
    }


    public static final class StudentBuilder {
        private Integer id;
        private String name;
        private String course;
        private Double fee;
        private Double tuition;
        private Seller seller;

        private StudentBuilder() {
        }

        public static StudentBuilder student() {
            return new StudentBuilder();
        }

        public StudentBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public StudentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public StudentBuilder course(String course) {
            this.course = course;
            return this;
        }

        public StudentBuilder fee(Double fee) {
            this.fee = fee;
            return this;
        }

        public StudentBuilder tuition(Double tuition) {
            this.tuition = tuition;
            return this;
        }

        public StudentBuilder seller(Seller seller) {
            this.seller = seller;
            return this;
        }

        public Student build() {
            Student student = new Student();
            student.id = this.id;
            student.tuition = this.tuition;
            student.course = this.course;
            student.name = this.name;
            student.fee = this.fee;
            student.seller = this.seller;
            return student;
        }
    }
}
