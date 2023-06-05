package com.api.sweetshop;

import com.api.sweetshop.model.Sweets;
import com.api.sweetshop.service.Baking;
import com.api.sweetshop.service.BakingImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(locations = {"classpath:application-context.xml", "classpath:test-sweets.xml"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class SweetshopApplicationTests {
    private static final String[] SWEETS =
            {"Chocolate truffles", "Fondant candies", "Homemade chocolate"};

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    Baking baking;

    @BeforeEach
    public void init() {
        SweetshopApplication.initialize(applicationContext);
    }

    @Test
    public void bakingBeanConfigurationTest() {
        assertNotNull(baking, "baking bean should be configured");
        assertTrue((baking instanceof BakingImpl),
                "repository should be instantiated with MapClientRepository class");
    }

    @Test
    public void sweet1Test() {
        Sweets sweet = baking.getSweetByName(SWEETS[0]);

        Integer price = Integer.parseInt(sweet.getPrice());
        assertNotNull(price, sweet.getName() + " should have a price");

        assertEquals(15, price);
    }

    @Test
    public void sweet2Test() {
        Sweets sweet = baking.getSweetByName(SWEETS[1]);

        Integer price = Integer.parseInt(sweet.getPrice());
        assertNotNull(price, sweet.getName() + " should have a price");

        assertEquals(10, price);
    }

    @Test
    public void sweet3Test() {
        Sweets sweet = baking.getSweetByName(SWEETS[2]);

        Integer price = Integer.parseInt(sweet.getPrice());
        assertNotNull(price, sweet.getName() + " should have a price");

        assertEquals(8, price);
    }

    @Test
    public void getSweetsCountTest() {
        ArrayList<Sweets> sweets = (ArrayList<Sweets>) baking.getAll();

        assertEquals(sweets.size(), 3);
    }

    @Test
    public void getSweetByIdTest() {
        Sweets sweet = baking.get(1L);

        assertEquals(sweet.getName(), SWEETS[1]);
    }
}
