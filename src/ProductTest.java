import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
Product p1;
Product p2;
Product p3;
    @BeforeEach
    void setUp() {
        p1 = new Product("Morot",10,"kg", "Grönt");
        p2 = new Product("Äpple",15, "st", "frukt");
        p3 = new Product("Banan",15, "st", "frukt");
    }

    //Här kan man skapa en calculatePrice test metod.


    @Test
    void getName() {
        assertEquals("Morot", p1.getName());
    }

    @Test
    void getPrice() {
        assertEquals(10, p1.getPrice());
        assertEquals(15, p2.getPrice());

    }

    @Test
    void getCategory() {
      String a = p1.getCategory();
      assertTrue(a.contains("Grönt"));
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
        assertTrue(p1.toString().endsWith("kr per " + p1.getUnit()));
        assertTrue(p2.toString().endsWith("kr per " + p2.getUnit()));
        assertTrue(p3.toString().endsWith("kr per " + p3.getUnit()));
    }
}