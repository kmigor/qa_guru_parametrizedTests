package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import tests.TestBase;
import util.Language;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ParametrizedTests extends TestBase {

    String baseHost = Language.RU.getUrl();

    @ParameterizedTest(name = "Для поискового запроса {0} должна открываться страница с заголовком {0}")
    @ValueSource(strings = {
            "Содержание", "Избранные статьи", "Текущие события"
    })
    void shouldOpenUtilPageTest(String page){
        open(baseHost);
        $("#p-navigation").find(byText(page)).click();
        $("#firstHeading").shouldHave(text(page));
    }

    @ParameterizedTest(name = "Для поискового запроса с параметром {0} должна открываться главная страница на языке {0}")
    @EnumSource(Language.class)
    void shouldOpenMainPageWithLanguageTest(Language language){
        Selenide.open(language.getUrl());
        $("#mw-content-text").shouldHave(text(language.getTitle()));
    }

    @ParameterizedTest(name = "Для поискового запроса с параметром {1} должна открываться главная страница с путем {0}, содержащая заголовок {1} и текст {2}")
    @CsvSource(value = {
            "/wiki/Java , Java , строго типизированный объектно-ориентированный язык программирования общего назначения",
            "/wiki/JUnit , JUnit , фреймворк для модульного тестирования программного обеспечения на языке Java.",
            "/wiki/Selenium , Selenium , инструмент для автоматизации действий веб-браузера."
    })
    void searchResultsShouldContainExpectedTitleAndText(String path, String title, String text){
        open(baseHost+path);
        $(".mw-page-title-main").shouldHave(text(title));
        $("#mw-content-text").shouldHave(text(text));
    }

}
