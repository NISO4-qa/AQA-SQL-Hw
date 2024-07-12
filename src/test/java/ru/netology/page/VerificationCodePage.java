package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerificationCodePage {
    private final SelenideElement codeField = $("[data-test-id='code'] input");
    private final SelenideElement verifyButton = $("[data-test-id='action-verify']");
    private final SelenideElement invalidNotificationText = $("[data-test-id='error-notification'] .notification__content");

    public VerificationCodePage() {
        verifyButton.shouldBe(Condition.visible);
    }

    private void makeValidCode(String code) {
        codeField.setValue(code);
        verifyButton.click();
    }

    public DashboardPage validCodeEnter(String code) {
        makeValidCode(code);

        return new DashboardPage();
    }

    public void invalidCodeEnter(String code) {
        makeValidCode(code);

        invalidNotificationText.shouldHave(Condition.text("Ошибка! Неверно указан код! Попробуйте ещё раз.")).shouldBe(Condition.visible);
    }
}