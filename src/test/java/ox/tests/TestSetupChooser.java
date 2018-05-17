package ox.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ox.app.languages.Language;
import ox.app.languages.Messenger;
import ox.app.utility.SetupChooser;

public class TestSetupChooser {

    @Test
    public static void setupChooserReturnsTrueWhenCustomCommandPassed() {
        // When
        boolean resultOfCheckCustom = SetupChooser.check(() -> "custom", s -> {
        }, new Messenger(Language.EN));
        // Then
        Assert.assertTrue(resultOfCheckCustom);
    }

    @Test
    public static void setupChooserReturnsFalseWhenDefaultCommandPassed() {
        // When
        boolean resultOfCheckDefault = SetupChooser.check(() -> "default", s -> {
        }, new Messenger(Language.EN));
        // Then
        Assert.assertFalse(resultOfCheckDefault);
    }
}
