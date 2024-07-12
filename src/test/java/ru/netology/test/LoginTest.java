package ru.netology.test;


import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;
import lombok.SneakyThrows;
import ru.netology.data.SQLHelper;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.SQLHelper.*;

public class LoginTest {

    LoginPage loginPage;

    @AfterAll
    static void delete() {
        deleteAll();
    }

    @BeforeEach
    void setup() {
        loginPage = open("http://localhost:9999", LoginPage.class);
    }

    @Test
    @DisplayName("Should successfully login to dashboard with exist login and password from sut test data")
    void shouldSuccessfulLogin() {
        var userInfo = DataHelper.getCorrectUserLogInInfo();
        var verificationPage = loginPage.validLogin(userInfo.getLogin(), userInfo.getPassword());
        verificationPage.validCodeEnter(getCode());
    }

    @Test
    @DisplayName("Should get error notification if user is not exist in base")
    void shouldGetErrorNotificationIfLoginWithRandomUserWithoutAddingToBase() {
        var userInfo = DataHelper.generateUser();
        loginPage.invalidLogin(userInfo.getLogin(), userInfo.getPassword());
    }

    @Test
    @DisplayName("Incorrect code")
    void shouldGetUnsuccessfulCode() {
        var userInfo = DataHelper.getCorrectUserLogInInfo();
        var verificationPage = loginPage.validLogin(userInfo.getLogin(), userInfo.getPassword());
        verificationPage.invalidCodeEnter(DataHelper.generateCode());
    }
}