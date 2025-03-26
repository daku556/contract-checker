package com.example.contract_checker.controller;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloControllerTest {
    @Test
    void testHello() {
        HelloController controller = new HelloController();
        String response = controller.hello("TestUser");
        assertThat(response).isEqualTo("Hello, TestUser!");
    }
}
