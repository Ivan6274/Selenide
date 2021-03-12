package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class SelenideTests {
    @Test
    void cardWithDelivery() {
        open("http://localhost:5555");
        $("[data-test-id='city']").setValue("Москва");
        $("calendar-input__native-control").clear();
        $("calendar-input__native-control").setValue("14.03.2021");
        $("[data-test-id='name']").setValue("Добрыня Никитич");
        $("[data-test-id='phone']").setValue("+76666666666");
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(Condition.visible).click();
        $(withText("Успешно")).shouldBe(Condition.visible, Duration.ofSeconds(10));




    }
}
