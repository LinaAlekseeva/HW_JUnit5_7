package guru.qa;
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
                Arguments.of(Locale.English, List.of("Contact information", "Investor relations","Useful links", "About Handelsbanken")),
                Arguments.of(Locale.Svenska, List.of("Kontakt","Investor relations","Användbara länkar","Om Handelsbanken"))

        );
    }

    @MethodSource("givenLocale")
    @ParameterizedTest(name = "Для локали {0} отображаются кнопки {1} ")
    @Tag("BLOCKER")
    void givenLocale(Locale locale,
                     List<String> buttons) {
        open("https://www.handelsbanken.com/en/");
        $$(".shb-sepu-header__header-top-inner").find(text(locale.name())).click();
        $$(".shb-sepu-app-footer__container")
                .filter(visible)
                .shouldHave(texts(buttons));


    }

}
