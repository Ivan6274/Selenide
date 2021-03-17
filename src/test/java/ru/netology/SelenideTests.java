package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.conditions.Visible;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class SelenideTests {
    @Test
    void cardWithDelivery() {
        open("http://localhost:8888");
        $("[data-test-id='city'] .input__control").setValue("Москва");
//        $("[data-test-id='date'] .calendar-input__native-control").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.BACK_SPACE);
//        LocalDate today = LocalDate.now();
//        LocalDate needDay = today.plusDays(3);
//        $("calendar-input__native-control").setValue(String.valueOf(needDay));
        $("[data-test-id='name'] .input__control").setValue("Добрыня Никитич");
        $("[data-test-id=\"phone\"] .input__control").setValue("+76666666666");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("[role=\"button\"] .button__content").click();
        $(withText("Успешно")).shouldBe(Condition.disappear, Duration.ofSeconds(10));
        $(withText("19.03.2021")).shouldBe(Condition.disappear, Duration.ofSeconds(10));





    }

    @Test
    void cardDeliveryWithCalendar(){
        open("http://localhost:8888");
        $("[data-test-id='city'] .input__control").setValue("Мо");
        $(withText("Москва")).click();
        $(By.cssSelector(("[data-test-id=\"date\"]  .input__box"))).click();
        int addDay = 17;
        LocalDate today = LocalDate.now();
        LocalDate needDay = today.plusDays(addDay);
        if ((needDay.getDayOfMonth() + addDay)>= LocalDate.MAX.getDayOfMonth()) {
            $(withText(String.valueOf(needDay.getDayOfMonth()))).click();
        }else{
            $(By.cssSelector(".calendar__title [class='calendar__arrow calendar__arrow_direction_right']")).click();
            $(byText(String.valueOf(needDay.getDayOfMonth()))).click();
        }
        $("[data-test-id='name'] .input__control").setValue("Добрыня Никитич");
        $("[data-test-id=\"phone\"] .input__control").setValue("+76666666666");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("[role=\"button\"] .button__content").click();
        $(withText("Успешно")).shouldBe(Condition.disappear, Duration.ofSeconds(10));
        $(withText("19.03.2021")).shouldBe(Condition.disappear, Duration.ofSeconds(10));
    }

}
