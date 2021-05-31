package com.example.cryptogramgamewithspring.Controllers.Commands.CommandSupplier;

import com.example.cryptogramgamewithspring.Controllers.Commands.Command;

public interface CommandSupplier<T extends Context> {
    Command fetchCommand(String command, T context);
}
