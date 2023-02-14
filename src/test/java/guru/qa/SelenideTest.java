package guru.qa;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import data.Locale;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class SelenideTest {
    static Stream<Arguments> givenLocale(){
        return Stream.of(
                Arguments.of(Locale.EN, List.of("Contact information", "Investor relations","Useful links", "About Handelsbanken")),
                Arguments.of(Locale.SV, List.of("Kontakt","Investor relations","Användbara länkar","Om Handelsbanken"))

        );
    }
    @BeforeAll
    static void beforeAll() {
        open("https://www.handelsbanken.com/en/");
        Configuration.browserSize = "1920x1080";
        Selenide.clearBrowserCookies();
    }
    @MethodSource("givenLocale")
    @ParameterizedTest(name = "Проверка корректности локали меню в {0} ")
    @Tag("BLOCKER")
    void givenLocale(Locale locale,
                     List<String> buttons) {
        $$(".shb-sepu-header__header-top-inner").find(text(locale.name())).click();
        $$(".hide-in-standalone-mode")
                .filter(visible)
                .shouldHave(texts(buttons));


    }

}
