package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

class SubmitFormTest {
    @Test
    void shouldSendFormToOrderDelivery() {
        open("http://0.0.0.0:9999");
        $("[data-test-id='city'] input").setValue("Чебоксары");
        $("[pattern]").setValue("11.05.2023");
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+71111111111");
        $("[data-test-id='agreement'] span").click();
        $(".button").click();
        $x("//div [contains(text(),'Встреча успешно забронирована на')]").should(Condition.appear, Duration.ofSeconds(12));
    }
}
