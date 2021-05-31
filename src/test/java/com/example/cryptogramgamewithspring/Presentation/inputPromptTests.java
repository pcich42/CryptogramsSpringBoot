package com.example.cryptogramgamewithspring.Presentation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import static org.junit.jupiter.api.Assertions.*;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemOut;
import static uk.org.webcompere.systemstubs.SystemStubs.withTextFromSystemIn;

@ExtendWith(SystemStubsExtension.class)
public class inputPromptTests {

    private ConsoleView view;

    @Test
    void getInputReturnsPassedInputAsArraySeparatedOnSpaces() throws Exception {
        String input = "I very ' much; liKe ,your hairstyle]/ (today) Mrs. Blue!";
        withTextFromSystemIn(input).
                execute(() -> {
                    view = new ConsoleView();
                    String[] output = view.getInput();
                    assertArrayEquals(new String[] {
                            "i",
                            "very",
                            "'",
                            "much;",
                            "like",
                            ",your",
                            "hairstyle]/",
                            "(today)",
                            "mrs.",
                            "blue!"
                    }, output);
                });
    }

    @Test
    void giveConfirmChoiceIsCalled_whenUserAnswerYes_returnTrue() throws Exception {
        String input = "yes";
        withTextFromSystemIn(input)
                .execute(() -> {
                    view = new ConsoleView();
                    assertTrue(view.confirmChoice());
                });
    }

    @Test
    void giveConfirmChoiceIsCalled_whenUserAnswerNo_returnFalse() throws Exception {
        String input = "no";
        withTextFromSystemIn(input)
                .execute(() -> {
                    view = new ConsoleView();
                    assertFalse(view.confirmChoice());
                });
    }

    @Test
    void giveConfirmChoiceIsCalled_whenUserAnswerRubbish_thenUserIsAskedAgain() throws Exception {
        String line1 = "rubbish";
        String line2 = "no";
        withTextFromSystemIn(line1, line2)
                .execute(() -> {
                    view = new ConsoleView();
                    String output = tapSystemOut(view::confirmChoice);
                    assertTrue(output.contains("Invalid response, try again with \"yes\" or \"no\" "));
                });
    }

}
