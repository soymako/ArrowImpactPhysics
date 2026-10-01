package me.soymako.tac.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import me.soymako.tac.TAC;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class ChangeArrowHitMultiplier implements CommandExecutor{

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
      @NotNull String @NotNull [] args) {

      float targetValue = 1f;
      try{
        targetValue = Float.valueOf(args[0]);
        
        TAC.instance.setMultiplier(targetValue);
        sender
          .sendMessage(
            TAC.mm.deserialize(
              String.format(
                "<green>Multiplicador de impacto cambiado a: <yellow>%.2f",
                targetValue
              )
            )
          );

      }
      catch (Exception e){
        sender.sendMessage(
          Component.text("el valor escrito debe de ser un numero!")
            .color(NamedTextColor.RED)
        );
      }


      return true;
  }

  


}
