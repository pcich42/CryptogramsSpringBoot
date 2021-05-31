package com.example.cryptogramgamewithspring.Controllers.Commands.CommandSupplier;

import com.example.cryptogramgamewithspring.Controllers.Commands.Command;
import com.example.cryptogramgamewithspring.Player.Player;

public interface CommandSupplier {
    Command fetchCommand(String[] input, Player player);
}
