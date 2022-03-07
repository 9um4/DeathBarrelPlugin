package io.github.sweetenpotato.deathbarrel.listener

import deathChest
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

class PlayerDeathListener : Listener {
    @EventHandler
    fun playerKilledEvent(event: PlayerDeathEvent) {
        val player = event.player
        player.deathChest()
    }
}