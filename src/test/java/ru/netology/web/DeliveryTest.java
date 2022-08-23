package ru.netology.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryTest {
    private DayPlus dayPlus = new DayPlus();
    private int plusDaysInput = 5;
    private int plusDaysVidget = 7;

    @Test
    void shouldFormDeliveryPositive() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Владимир");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(dayPlus.dayPlus(plusDaysInput));
        $("[data-test-id='name'] input").setValue("Сергей Сергеев");
        $("[data-test-id='phone'] input").setValue("+71234567890");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification']").shouldBe(appear, Duration.ofSeconds(15));
        $("[data-test-id='notification']").shouldHave(text(dayPlus.dayPlus(plusDaysInput)));
    }

    @Test
    void shouldFormDeliveryPositiveWithList() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("аб");
        $$(".menu-item__control").last().click();
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $(".input__icon").click();
        if (dayPlus.monthCheck(plusDaysVidget)) {
            $("[data-step='1']").click();
        }
//        String day = dayPlus.day(plusDaysVidget);
        $$(".calendar__day").find(exactText(dayPlus.day(plusDaysVidget))).click();
        $("[data-test-id='name'] input").setValue("Сергей Сергеев");
        $("[data-test-id='phone'] input").setValue("+71234567890");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification']").shouldBe(appear, Duration.ofSeconds(15));
        $("[data-test-id='notification']").shouldHave(text(dayPlus.dayPlus(plusDaysVidget)));
    }

}
