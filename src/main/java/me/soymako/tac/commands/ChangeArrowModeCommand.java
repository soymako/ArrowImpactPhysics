package me.soymako.tac.commands;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import me.soymako.tac.TAC;
import me.soymako.tac.TAC.ARROW_MODE;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class ChangeArrowModeCommand implements CommandExecutor, TabCompleter{


  // /arrow_mode <target:enum:int>
  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
      @NotNull String @NotNull [] args) {

      if (args.length == 0){
        sender.sendMessage(
          TAC.mm.deserialize(
            "<red>Uso equivocado! Uso debe de ser"+
            "<green>/arrow_mode VECTOR"+
            "<reset>modos:"+
            "<yellow> DEFAULT, VECTOR, VELOCITY"
          )
        );
        return true;
      }
      TAC.ARROW_MODE target = TAC.ARROW_MODE.valueOf(args[0]);

      TAC.instance.setMode(target);
      sender.sendMessage(
        TAC.mm.deserialize(
          String.format(
            "se cambio el modo a <green>%s", target.toString()
          )
        )
      );

      return true;



  }

  @Override
  public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command,
    @NotNull String label, @NotNull String @NotNull [] args) {
    if (!(sender instanceof Player)) return List.of();

    if (args.length == 1){
      return Arrays
        .stream(TAC.ARROW_MODE.values())
        .map(Enum::name)
        .toList();
    }



    return List.of();
  }




}
