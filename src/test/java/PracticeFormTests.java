import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PracticeFormTests {
    RegistrationPage registrationPage = new RegistrationPage();

    String firstName = "FirstName";
    String lastName = "LastName";
    String gender = "Male";
    String number = "1798765432";

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
    }

    @Test
    void positiveFillTest() {

        registrationPage.openPage();
        registrationPage.typeFirstName(firstName);
        registrationPage.typeLastName(lastName);
        registrationPage
                .typeEmail()
                .selectGender(gender)
                .typeUserNumber(number)
                .setDateOfBirth("28","July","2005");


        $("#subjectsInput").val("maths").pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#uploadPicture").uploadFromClasspath("./img/1.png");
        $("#currentAddress").val("Some Address, dom 1, kv 7");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();

        registrationPage.checkResultsTitle();
        registrationPage.checkResultsValue();
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("FirstName LastName"));
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("FirstName LastName"));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("qqq@www.eee"));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Male"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(number));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("28 July,2005"));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("Maths"));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Reading"));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("1.png"));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text("Some Address, dom 1, kv 7"));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("NCR Delhi"));
    }
}
