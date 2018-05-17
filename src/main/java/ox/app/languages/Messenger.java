package ox.app.languages;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messenger {
    private static final String BLANK_SPACE = " ";
    private ResourceBundle data;

    public Messenger(Language language) {
        Locale locale = new Locale(language.getLang(), language.getCountry());

        this.data = ResourceBundle.getBundle("Messages", locale);
    }

    public String welcomeMessage() {
        return data.getString("welcomeMessage");
    }

    public String startRoundMessage() {
        return data.getString("startRoundMessage");
    }

    public String drawMessage() {
        return data.getString("drawMessage");
    }

    public String pressEnterMessage() {
        return data.getString("pressEnterMessage") + BLANK_SPACE;
    }

    public String exitAppMessage() {
        return data.getString("exitAppMessage");
    }

    public String endGameMessage() {
        return data.getString("endGameMessage");
    }

    public String winsTheRoundMessage() {
        return BLANK_SPACE + data.getString("winsTheRoundMessage");
    }

    public String pleaseChooseSymbolInstruction() {
        return data.getString("pleaseChooseSymbolInstruction");
    }

    public String theWinnerIsMessage() {
        return data.getString("theWinnerIsMessage") + BLANK_SPACE;
    }

    public String pointsMessage() {
        return BLANK_SPACE + data.getString("pointsMessage") + BLANK_SPACE;
    }

    public String pleaseProvideInstruction() {
        return data.getString("pleaseProvideInstruction") + BLANK_SPACE;
    }

    public String playerNameMessage() {
        return BLANK_SPACE + data.getString("playerNameMessage");
    }

    public String customSettingsMessage() {
        return data.getString("customSettingsMessage");
    }

    public String defaultSettingsMessage() {
        return data.getString("defaultSettingsMessage");
    }

    public String wrongCommandMessage() {
        return data.getString("wrongCommandMessage") + BLANK_SPACE;
    }

    public String stringIsNotIntegerOrExceedsError() {
        return data.getString("stringIsNotIntegerOrExceedsError");
    }

    public String validateHeightMessage() {
        return data.getString("validateHeightMessage");
    }

    public String validateWidthMessage() {
        return data.getString("validateWidthMessage");
    }

    public String wrongCoordinateError() {
        return data.getString("wrongCoordinateError");
    }

    public String numberExceedsTheBoardError() {
        return data.getString("numberExceedsTheBoardError");
    }

    public String positionAlreadyTakenError() {
        return data.getString("positionAlreadyTakeError");
    }

    public String nameCannotBeEmptyError() {
        return data.getString("nameCannotBeEmptyError");
    }

    public String symbolNotSupportedError() {
        return data.getString("symbolNotSupportedError");
    }

    public String passNumberOfStrokesToWinMessage() {
        return data.getString("passNumberOfStrokesToWinMessage");
    }

    public String exceedsBoardWidthError() {
        return data.getString("exceedsBoardWidthError");
    }

    public String exceedsBoardHeightError() {
        return data.getString("exceedsBoardHeightError");
    }

    public String stringIsNotIntegerError() {
        return data.getString("stringIsNotIntegerError");
    }

    public String defaultFirstPlayerName() {
        return data.getString("defaultFirstPlayerName");
    }

    public String defaultSecondPlayerName() {
        return data.getString("defaultSecondPlayerName");
    }
}
