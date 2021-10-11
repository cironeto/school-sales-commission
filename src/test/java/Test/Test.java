package Test;

import dev.cironeto.domain.Seller;
import lombok.extern.log4j.Log4j2;
import dev.cironeto.service.SellerService;

import java.util.List;

@Log4j2
public class Test {

    public static void main(String[] args) {
        List<Seller> sellers = SellerService.findByName("Ciro");
        System.out.println(sellers);
        log.info(sellers);







    }
}
