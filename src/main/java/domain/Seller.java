package domain;

import java.util.Objects;

public class Seller {
    private Integer id;
    private String name;
    private Double salary;
    private Double commission;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getSalary() {
        return salary;
    }

    public Double getCommission() {
        return commission;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", commission=" + commission +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return Objects.equals(id, seller.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public static final class SellerBuilder {
        private Integer id;
        private String name;
        private Double salary;
        private Double commission;

        private SellerBuilder() {
        }

        public static SellerBuilder seller() {
            return new SellerBuilder();
        }

        public SellerBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public SellerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SellerBuilder salary(Double salary) {
            this.salary = salary;
            return this;
        }

        public SellerBuilder commission(Double commission) {
            this.commission = commission;
            return this;
        }

        public Seller build() {
            Seller seller = new Seller();
            seller.salary = this.salary;
            seller.id = this.id;
            seller.commission = this.commission;
            seller.name = this.name;
            return seller;
        }
    }
}
