package ox.app.languages;

import java.util.Locale;
import java.util.ResourceBundle;

public class InstructionDriver {
    private ResourceBundle instructionData;

    public InstructionDriver(Language language) {
        Locale locale = new Locale(language.getLang(), language.getCountry());
        this.instructionData = ResourceBundle.getBundle("Instructions", locale);
    }

    public String welcome() {
        return instructionData.getString("welcome");
    }
    public String startRound() {
        return instructionData.getString("startRound");
    }
    public String draw() {
        return instructionData.getString("draw");
    }
    public String pressEnter() {
        return instructionData.getString("startRoundInstruction");
    }
    public String exitAppMessage() {
        return instructionData.getString("exitAppMessage");
    }
    public String endGameMessage() {
        return instructionData.getString("endGameMessage");
    }
    public String winsTheRoundMessage() {
        return instructionData.getString("winsTheRoundMessage");
    }
    public String pleaseChooseSymbolInstruction() {
        return instructionData.getString("pleaseChooseSymbolInstruction");
    }
}
