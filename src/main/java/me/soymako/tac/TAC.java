package me.soymako.tac;

import org.bukkit.plugin.java.JavaPlugin;

import me.soymako.tac.commands.ChangeArrowHitMultiplier;
import me.soymako.tac.commands.ChangeArrowModeCommand;
import net.kyori.adventure.text.minimessage.MiniMessage;

public final class TAC extends JavaPlugin{

  public static enum ARROW_MODE{
    DEFAULT,
    VECTOR,
    VELOCITY
  }

  public static MiniMessage mm = MiniMessage.miniMessage();

  private ARROW_MODE mode = ARROW_MODE.DEFAULT;
  private float multiplier = 1f;

  public static TAC instance;

  public ARROW_MODE getMode(){
    return this.mode;
  }

  public void setMultiplier(float value){
    this.multiplier = value;
    getConfig().set("arrow_hit_multiplier", value);
  }

  public float getMultiplier(){
    return this.multiplier;
  }

  public void setMode(ARROW_MODE targetMode){
    ARROW_MODE prev = this.mode;
    this.mode = targetMode;
    getLogger().info(
      String.format("modo cambiado!\nprevio: %s\nnuevo:%s", prev, getMode())
    );
    this.getConfig().set("arrow_mode", targetMode.name());
    this.saveConfig();
  }

  @Override
  public void onEnable(){
    instance = this;
    this.saveDefaultConfig();
    this.mode = ARROW_MODE.valueOf(getConfig().getString("arrow_mode", "DEFAULT"));


    getCommand("arrow_mode").setExecutor(new ChangeArrowModeCommand());
    getCommand("arrow_hit_multiplier").setExecutor(new ChangeArrowHitMultiplier());

    getServer().getPluginManager().registerEvents(new Listeners(), this);

  }

  @Override
  public void onDisable(){
    getLogger().info("plugin desactivado");


  }



}
