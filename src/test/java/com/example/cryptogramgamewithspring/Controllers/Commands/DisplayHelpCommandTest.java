package com.example.cryptogramgamewithspring.Controllers.Commands;

import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.willCallRealMethod;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemOut;

@ExtendWith(MockitoExtension.class)
class DisplayHelpCommandTest {

    @Mock
    private ConsoleView view;

    @Test
    void executeDisplaysHelpPrompt() throws Exception {
        willCallRealMethod().given(view).displayMessage(anyString());
        List<String> descriptions = List.of("a", "b", "c");
        DisplayHelpCommand command = new DisplayHelpCommand(view, descriptions);
        String output = tapSystemOut(command::execute);
        assertEquals(
                ">>>>> All available commands are:\n" +
                        "a\n" +
                        "b\n" +
                        "c\n" +
                        "\n",
                output);
    }

}