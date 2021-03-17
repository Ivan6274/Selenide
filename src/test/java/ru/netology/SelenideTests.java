package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.conditions.Visible;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.text.DecimalFormat;
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
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.BACK_SPACE);
        LocalDate today = LocalDate.now();
        LocalDate needDay = today.plusDays(3);
        $("[data-test-id='date'] .input__control").setValue(new DecimalFormat("00").format(needDay.getDayOfMonth())+"."+ new DecimalFormat("00").format(needDay.getMonthValue())+"."+ needDay.getYear() );
        $("[data-test-id='name'] .input__control").setValue("Добрыня Никитич");
        $("[data-test-id=\"phone\"] .input__control").setValue("+76666666666");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $("[role=\"button\"] .button__content").click();
        $(withText("Успешно")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(withText(new DecimalFormat("00").format(needDay.getDayOfMonth())+"."+ new DecimalFormat("00").format(needDay.getMonthValue())+"."+ needDay.getYear() )).shouldBe(Condition.visible, Duration.ofSeconds(15));





    }

    @Test
    void cardDeliveryWithCalendar(){
        open("http://localhost:8888");
        $("[data-test-id='city'] .input__control").setValue("Мо");
        $(withText("Москва")).click();
        $(By.cssSelector(("[data-test-id=\"date\"]  .input__box"))).click();
        int addDay = 7;
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
        $(withText("Успешно")).shouldBe(Condition.visible, Duration.ofSeconds(25));
        $(withText(new DecimalFormat("00").format(needDay.getDayOfMonth())+"."+ new DecimalFormat("00").format(needDay.getMonthValue())+"."+ needDay.getYear() )).shouldBe(Condition.visible, Duration.ofSeconds(25));
    }

}
