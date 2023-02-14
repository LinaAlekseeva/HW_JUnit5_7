package guru.qa;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.testng.annotations.AfterMethod;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;



public class JUnitSimpleTest {
    @BeforeAll
    static void beforeAll() {
        open("https://www.handelsbanken.com/en/");
        Configuration.browserSize = "1920x1080";
    }
    @AfterMethod
    @CsvSource(value = {"Сredit, https://www.handelsbanken.com/en/investor-relations/debt-investors/rating ",
            "Contribution, https://www.handelsbanken.com/en/sustainability/news/green-bond-impact"})
    @ParameterizedTest(name = "В адресе поиска на сайте банка {1} должна появится нужная категория {0}")
    @Tag("BLOCKER")
    void SearchTest(
            String Name,
            String productCategory
    ) {
        $("input").setValue(Name).$(".shb-input-search__search-button").click();
        $(".shb-sepu-app-footer__child").shouldHave(text(productCategory)).clear();
    }

}