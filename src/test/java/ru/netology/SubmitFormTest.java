package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

class SubmitFormTest {
    public String generateDate(int days) {
        LocalDate LocalDate = null;
        DateTimeFormatter DateTimeFormatter = null;
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    String planningDate = generateDate(4);

    @Test
    void shouldSendFormToOrderDelivery() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Чебоксары");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+71111111111");
        $("[data-test-id='agreement'] span").click();
        $(".button").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(12))
                .shouldBe(Condition.visible);
    }
}
