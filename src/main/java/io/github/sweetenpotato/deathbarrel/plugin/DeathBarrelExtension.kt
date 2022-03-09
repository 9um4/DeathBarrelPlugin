package io.github.sweetenpotato.deathbarrel.plugin

import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Barrel
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import kotlin.math.ceil
import kotlin.math.round

fun Player.deathChest() {
    val deathBarrelLocation = ArrayList<Location>()
    val playerItemList = ArrayList<ItemStack>()
    val rawLocation = this.location.clone()

    // 플레이어가 소지하고 있는 모든 아이템을 리스트에 옮겨 담음
    for (item in this.inventory.contents!!) {
        if (item != null) {
            playerItemList.add(item)
        }
    }
    for (item in this.inventory.armorContents!!) {
        if (item != null) {
            playerItemList.add(item)
        }
    }

    // 차원 별 통 생성 위치 설정 : y좌표
    if (ceil(rawLocation.y) >= this.world.maxHeight.toDouble()) {
        rawLocation.y = (this.world.maxHeight - 1).toDouble()
    }
    else if (ceil(rawLocation.y) <= this.world.minHeight.toDouble()) {
        rawLocation.y = (this.world.minHeight + 1).toDouble()
    }
    else {
        rawLocation.y = ceil(rawLocation.y)
    }
    rawLocation.x = round(rawLocation.x)
    rawLocation.z = round(rawLocation.z)

    deathBarrelLocation.add(rawLocation)

    // 플레이어가 들고 있었던 아이템을 떨구는 통 생성
    if (playerItemList.size > 27) {
        rawLocation.x += 1.0
        deathBarrelLocation.add(rawLocation)

        deathBarrelLocation[0].block.type = Material.BARREL
        val barrel1: Barrel = deathBarrelLocation[0].block.state as Barrel
        val barrel2: Barrel = deathBarrelLocation[1].block.state as Barrel

        barrel1.inventory.contents = playerItemList.subList(0, 27).toTypedArray()
        barrel2.inventory.contents = playerItemList.subList(27, playerItemList.size).toTypedArray()
    }
    else if (playerItemList.size > 0) {
        deathBarrelLocation[0].block.type = Material.BARREL
        val barrel: Barrel = deathBarrelLocation[0].block.state as Barrel

        barrel.inventory.contents = playerItemList.toTypedArray()
    }
    else return
    this.inventory.clear()
    if (this.hasPermission("deathBarrel.getMessage")) {
        this.sendMessage(
            "${ChatColor.WHITE}[${ChatColor.AQUA}Info${ChatColor.WHITE}] 플레이어 상자 위치 : world = " + deathBarrelLocation[0].world.name + ", x = " + deathBarrelLocation[0].x.toString() + ", y = " + deathBarrelLocation[0].y.toString() + ", z = " + deathBarrelLocation[0].z.toString()
        )
    }
}