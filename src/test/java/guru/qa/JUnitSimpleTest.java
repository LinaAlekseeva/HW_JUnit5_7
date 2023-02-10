package guru.qa;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class JUnitSimpleTest {
    @BeforeAll
    static void beforeAll() {
        open("https://sber.ru/search/?text=&searchid=2337263&web=0");
        Configuration.browserSize = "1920x1080";
    }

    @CsvSource(value = {"Кредит, sberbank.ru/ru/person ",
            "Вклад, sberbank.com/ru/person/contributions/deposits"})
    @ParameterizedTest(name = "В адресе поиска на сайте банка {1} должна появится нужная категория {0}")
    @Tag("BLOCKER")
    void SearchTest(
            String Name,
            String productCategory
    ) {
        $("input").setValue(Name).pressEnter();
        $(".b-serp-url__item").shouldHave(text(productCategory));
    }

}