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

        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GREEN + plugin.getConfig().getString("interface_name"));

        ItemStack serverItem = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta meta = serverItem.getItemMeta();

        assert meta != null;
        meta.setDisplayName(ChatColor.AQUA + plugin.getConfig().getString("switch_item_name"));
        serverItem.setItemMeta(meta);

        inv.setItem(13, serverItem);
        player.openInventory(inv);

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if (event.getView().getTitle().equals(ChatColor.GREEN + plugin.getConfig().getString("interface_name"))) {

            event.setCancelled(true);
            event.setResult(Event.Result.DENY);

            Player player = (Player) event.getWhoClicked();

            if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.GRASS_BLOCK) {

                if (event.isLeftClick() || event.isRightClick()) {

                    player.sendMessage(ChatColor.GREEN + plugin.getConfig().getString("switching_message"));

                    player.closeInventory();

                }

            }

            //ByteArrayOutputStream b = new ByteArrayOutputStream();
            //DataOutputStream out = new DataOutputStream(b);

            //try {

            //out.writeUTF("Connect");
            //out.writeUTF(Objects.requireNonNull(plugin.getConfig().getString("server")));

            //} catch (IOException e) {

            //player.sendMessage(ChatColor.RED + "Failed to connect " + plugin.getConfig().getString("server") + " server.");

            //}

        }

    }

}
