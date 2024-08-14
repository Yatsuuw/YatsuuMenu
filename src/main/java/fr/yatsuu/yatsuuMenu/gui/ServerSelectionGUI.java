package fr.yatsuu.yatsuuMenu.gui;

import fr.yatsuu.yatsuuMenu.YatsuuMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

public class ServerSelectionGUI implements Listener {

    private final YatsuuMenu plugin;

    public ServerSelectionGUI(YatsuuMenu plugin) {

        this.plugin = plugin;
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);

    }

    public void openInventory(Player player) {

        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("interface_name")));

        ItemStack serverItem = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta meta = serverItem.getItemMeta();

        assert meta != null;
        meta.setDisplayName(ChatColor.AQUA + plugin.getConfig().getString("switch_item_name"));
        serverItem.setItemMeta(meta);

        inv.setItem(13, serverItem);
        player.openInventory(inv);

    }

}
