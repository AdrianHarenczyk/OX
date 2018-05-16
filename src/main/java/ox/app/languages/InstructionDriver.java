package ox.app.languages;

import java.util.Locale;
import java.util.ResourceBundle;

public class InstructionDriver {
    private ResourceBundle instructionData;
    private static final String BLANK_SPACE = " ";

    public InstructionDriver(Language language) {
        Locale locale = new Locale(language.getLang(), language.getCountry());

        this.instructionData = ResourceBundle.getBundle("Instructions", locale);
    }

    public String welcomeMessage() {
        return instructionData.getString("welcomeMessage");
    }

    public String startRoundMessage() {
        return instructionData.getString("startRoundMessage");
    }

    public String drawMessage() {
        return instructionData.getString("drawMessage");
    }

    public String pressEnterMessage() {
        return instructionData.getString("pressEnterMessage") + BLANK_SPACE;
    }

    public String exitAppMessage() {
        return instructionData.getString("exitAppMessage");
    }

    public String endGameMessage() {
        return instructionData.getString("endGameMessage");
    }

    public String winsTheRoundMessage() {
        return BLANK_SPACE + instructionData.getString("winsTheRoundMessage");
    }

    public String pleaseChooseSymbolInstruction() {
        return instructionData.getString("pleaseChooseSymbolInstruction");
    }

    public String theWinnerIsMessage() {
        return instructionData.getString("theWinnerIsMessage") + BLANK_SPACE;
    }

    public String pointsMessage() {
        return BLANK_SPACE + instructionData.getString("pointsMessage") + BLANK_SPACE;
    }

    public String pleaseProvideInstruction() {
        return instructionData.getString("pleaseProvideInstruction") + BLANK_SPACE;
    }

    public String playerNameMessage() {
        return BLANK_SPACE + instructionData.getString("playerNameMessage");
    }

    public String customSettingsMessage() {
        return instructionData.getString("customSettingsMessage");
    }

    public String defaultSettingsMessage() {
        return instructionData.getString("defaultSettingsMessage");
    }

    public String wrongCommandMessage() {
        return instructionData.getString("wrongCommandMessage") + BLANK_SPACE;
    }

    public String stringIsNotIntegerOrExceedsError() {
        return instructionData.getString("stringIsNotIntegerOrExceedsError");
    }

    public String validateHeightMessage() {
        return instructionData.getString("validateHeightMessage");
    }

    public String validateWidthMessage() {
        return instructionData.getString("validateWidthMessage");
    }

    public String wrongCoordinateError() {
        return instructionData.getString("wrongCoordinateError");
    }

    public String numberExceedsTheBoardError() {
        return instructionData.getString("numberExceedsTheBoardError");
    }

    public String positionAlreadyTakenError() {
        return instructionData.getString("positionAlreadyTakeError");
    }

    public String nameCannotBeEmptyError() {
        return instructionData.getString("nameCannotBeEmptyError");
    }

    public String symbolNotSupportedError() {
        return instructionData.getString("symbolNotSupportedError");
    }

    public String passNumberOfStrokesToWinMessage() {
        return instructionData.getString("passNumberOfStrokesToWinMessage");
    }

    public String exceedsBoardWidthError() {
        return instructionData.getString("exceedsBoardWidthError");
    }

    public String exceedsBoardHeightError() {
        return instructionData.getString("exceedsBoardHeightError");
    }

    public String stringIsNotIntegerError() {
        return instructionData.getString("stringIsNotIntegerError");
    }

    public String defaultFirstPlayerName() {
        return instructionData.getString("defaultFirstPlayerName");
    }

    public String defaultSecondPlayerName() {
        return instructionData.getString("defaultSecondPlayerName");
    }
}
