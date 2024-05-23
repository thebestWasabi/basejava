package ru.maxim_khamzin.webapp.model;

public enum ContactType {
    PHONE("Тел."),
    MOBILE("Мобильный тел."),
    HOME_PHONE("Домашний тел."),
    SKYPE("Skype"),
    TELEGRAM("Telegram"),
    MAIL("Почта"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Про StackOverflow"),
    HOME_PAGE("Домашняя страница");

    private final String title;

    ContactType(final String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
