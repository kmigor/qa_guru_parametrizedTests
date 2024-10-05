package util;

public enum Language {
    RU("https://ru.wikipedia.org", "Добро пожаловать в Википедию,"),
    EN("https://en.wikipedia.org", "Welcome to Wikipedia"),
    IT("https://it.wikipedia.org", "Benvenuti su Wikipedia");

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    private final String url;
    private final String title;

    Language(String url, String title){
        this.url = url;
        this.title = title;
    }


}
