package me.soymako.tac;

import java.text.BreakIterator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.WindCharge;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

public class Listeners implements Listener{

  @EventHandler
  public void onCollide(ProjectileHitEvent e){
    Entity target = e.getHitEntity();
    if (target == null) return;
    if (!(e.getEntity() instanceof Arrow arrow)) return;

    EntityType t = target.getType();
    if (!(t == EntityType.FIREBALL || t == EntityType.WIND_CHARGE)) return;

    // Bukkit.broadcast(
    //   Component.text("Collision cancelled! but now is better!")
    //   .color(NamedTextColor.RED)
    // );

    e.setCancelled(handleCollision(arrow, target));
  }

  private boolean handleCollision(Arrow arrow, Entity hit){
    TAC.ARROW_MODE mode = TAC.instance.getMode();
    switch (mode){
      case VECTOR -> {
        Vector arrowPos = arrow.getLocation().toVector();
        Vector hitPos = hit.getLocation().toVector();

        Vector direction = (hitPos.subtract(arrowPos)).normalize();


        hit.setVelocity(direction.multiply(TAC.instance.getMultiplier()));
        return true;

      }
      case VELOCITY -> {
        hit.setVelocity(
          arrow.getVelocity()
            .normalize()
            .multiply(
              TAC.instance.getMultiplier()
            )
        );
        arrow.setVelocity(new Vector(0,0,0));
        return true;
      }
      default -> {
      }
    }
    return false;
  }



}
