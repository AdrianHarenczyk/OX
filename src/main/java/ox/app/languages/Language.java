package ox.app.languages;

public enum Language {
    EN("en","GB"),
    PL("pl","PL");

    String language;
    String country;

    Language(String language, String country) {
        this.language = language;
        this.country = country;
    }
    public String getLang() {
        return language;
    }
    public String getCountry() {
        return country;
    }
}
