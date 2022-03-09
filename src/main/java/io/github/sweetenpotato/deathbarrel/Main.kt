package io.github.sweetenpotato.deathbarrel

import io.github.sweetenpotato.deathbarrel.listener.PlayerDeathListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    private val playerDeathListener = PlayerDeathListener()
    override fun onEnable() {
        logger.info("DeathBarrel Plugin v1.1 Enabled")
        Bukkit.getPluginManager().registerEvents(playerDeathListener, this)
    }

    override fun onDisable() {
        logger.info("DeathBarrel Plugin v1.1 Disabled")
    }
}