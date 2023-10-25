import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
Product p1;
Product p2;
Product p3;
    @BeforeEach
    void setUp() {
        p1 = new Product("Morot",10,"GRÖNT");
        p2 = new Product("Äpple",12, "FRUKT");
        p3 = new Product("Banan",15, "FRUKT");
    }

    @Test
    void getName() {
        assertEquals("Morot", p1.getName());
    }

    @Test
    void getPrice() {
        assertEquals(10, p1.getPrice());
        assertEquals(0, p2.getPrice());

    }

    @Test
    void getCategory() {
      String a = p1.getCategory();
      assertTrue(a.contains("Grönt") && a.contains("Rotfrukt"));
    }



    @Test
    void setName() {
        p1.setName("Morrot");
        assertEquals("Morrot", p1.getName());
    }

    @Test
    void setPrice() {
        p1.setPrice(20);
        assertEquals(20, p1.getPrice());
    }

    @Test
    void setCategory() {

    }



    @Test
    void toStringTest() {
        assertTrue(p1.toString().endsWith("kr/kg i kategorin [Grönt, Rotfrukt]"));
        assertTrue(p2.toString().endsWith(" i kategorin [Grönt, Rotfrukt] *inget pris inlagt*"));
    }
}