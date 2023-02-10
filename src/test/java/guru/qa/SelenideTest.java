package guru.qa;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class SelenideTest {
    static Stream<Arguments> givenLocale(){
        return Stream.of(
                Arguments.of(Locale.ENGLISH),
                Arguments.of(Locale)

        );
    }

    @MethodSource
    @ParameterizedTest(name = "Для локали {0} отображаются кнопки {1} ")
    @Tag("BLOCKER")
    void givenLocale(Locale locale,
                     List<String> buttons) {
        open("http://www.sberbank.ru/ru/person/credits/money");
        $$(".kitt-header__link kitt-header__lang kitt-header__link kitt-header__lang_en").find(text(locale.name())).click();
        $$(".header__a2QKp5").shouldHave(texts(buttons));
    }
}
