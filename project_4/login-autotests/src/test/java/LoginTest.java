import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    @Test
    void loginAndLogoutTest() {
        Configuration.browserSize = "1920x1080";
        open("http://qa-stand-login.inzhenerka.tech/login");

        $("#username").setValue("admin");
        $(By.name("password")).setValue("admin123");
        $("button[type='submit']").click();

        $(byText("Выйти")).shouldBe(visible);

        $(byText("Выйти")).click();

        $("button[type='submit']").shouldBe(visible);
    }

    @Test
    void loginWithDifferentLocators() {
        open("http://qa-stand-login.inzhenerka.tech/login");
        $("#username").shouldBe(visible).setValue("admin");

        $(By.name("password")).shouldBe(visible).setValue("admin123");

        $(By.xpath("//button[text()='Войти']")).shouldBe(visible).click();

        $(byText("Выйти")).shouldBe(visible);
    }

    @Test
    void loginAndLogoutUsingTextSelectors() {
        open("http://qa-stand-login.inzhenerka.tech/login");

        $("body").shouldHave(text("Основы QA"));

        $(By.xpath("//input[@name='username']")).shouldBe(visible).setValue("admin");
        $(By.xpath("//input[@name='password']")).shouldBe(visible).setValue("admin123");

        $(byText("Войти")).shouldBe(visible).click();

        $("h2").shouldHave(text("Привет"));

        $(byText("Выйти")).shouldBe(visible).click();
    }

    @Test
    void emptyLoginAndPassword() {

        //part 3
        open("http://qa-stand-login.inzhenerka.tech/login");

        $("#username").setValue("");
        $(By.name("password")).setValue("");
        $("button[type='submit']").click();

        $("body").shouldHave(text("Вход не выполнен"));
    }

    @Test
    void emptyLogin() {
        open("http://qa-stand-login.inzhenerka.tech/login");

        $("#username").setValue("");
        $(By.name("password")).setValue("admin123");
        $("button[type='submit']").click();

        $("body").shouldHave(text("Вход не выполнен"));
    }

    @Test
    void emptyPassword() {
        open("http://qa-stand-login.inzhenerka.tech/login");

        $("#username").setValue("admin");
        $(By.name("password")).setValue("");
        $("button[type='submit']").click();

        $("body").shouldHave(text("Вход не выполнен"));
    }

    @Test
    void testuserLoginAndPassword() {
        open("http://qa-stand-login.inzhenerka.tech/login");

        $("#username").setValue("testuser");
        $(By.name("password")).setValue("password123");
        $("button[type='submit']").click();

        $("body").shouldHave(text("Тестировщик"));

        $(byText("Выйти")).shouldBe(visible).click();
    }

    @Test
    void wrongAdminLogin() {
        open("http://qa-stand-login.inzhenerka.tech/login");

        $("#username").setValue("admiiin");
        $(By.name("password")).setValue("admin123");
        $("button[type='submit']").click();

        $(byText("Выйти")).shouldNotBe(visible);
    }

    @Test
    void wrongAdminPassword() {
        open("http://qa-stand-login.inzhenerka.tech/login");

        $("#username").setValue("admin");
        $(By.name("password")).setValue("777admin777");
        $("button[type='submit']").click();

        $(byText("Выйти")).shouldNotBe(visible);
    }

    @Test
    void wrongTestuserLogin() {
        open("http://qa-stand-login.inzhenerka.tech/login");

        $("#username").setValue("testeruser_wrong");
        $(By.name("password")).setValue("password123");
        $("button[type='submit']").click();

        $(byText("Выйти")).shouldNotBe(visible);
    }

    @Test
    void wrongTestuserPassword() {
        open("http://qa-stand-login.inzhenerka.tech/login");

        $("#username").setValue("testuser");
        $(By.name("password")).setValue("password12345");
        $("button[type='submit']").click();

        $(byText("Выйти")).shouldNotBe(visible);
    }

    @Test
    void specialCharacters() {
        open("http://qa-stand-login.inzhenerka.tech/login");

        $("#username").setValue("!@#$%^&*");
        $(By.name("password")).setValue("&*(/!@^#");
        $("button[type='submit']").click();

        $(byText("Выйти")).shouldNotBe(visible);
    }

    @Test
    void numbers() {
        open("http://qa-stand-login.inzhenerka.tech/login");

        $("#username").setValue("12345678");
        $(By.name("password")).setValue("123456");
        $("button[type='submit']").click();

        $(byText("Выйти")).shouldNotBe(visible);
    }

    @Test
    void capslockLoginAndPassword() {
        open("http://qa-stand-login.inzhenerka.tech/login");

        $("#username").setValue("ADMIN");
        $(By.name("password")).setValue("ADMIN123");
        $("button[type='submit']").click();

        $(byText("Выйти")).shouldNotBe(visible);
    }

    @Test
    void sqlInjectionInLogin() {
        open("http://qa-stand-login.inzhenerka.tech/login");

        $("#username").setValue("' OR '1'='1");
        $(By.name("password")).setValue("password123");
        $(byText("Войти")).click();

        $(byText("Выйти")).shouldNotBe(visible);
    }
}