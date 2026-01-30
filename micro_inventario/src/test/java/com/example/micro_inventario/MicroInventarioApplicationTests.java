package com.example.micro_inventario;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")   // 👈 Forzamos a usar application-test.properties
class MicroInventarioApplicationTests {

    @Test
    void contextLoads() {
        // Este test solo valida que el contexto de Spring arranca correctamente
    }
}

