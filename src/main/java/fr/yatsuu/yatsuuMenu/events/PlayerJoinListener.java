package fr.yatsuu.yatsuuMenu.events;

import fr.yatsuu.yatsuuMenu.YatsuuMenu;
import fr.yatsuu.yatsuuMenu.gui.ServerSelectionGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerJoinListener implements Listener {

    private final YatsuuMenu plugin;

    public PlayerJoinListener(YatsuuMenu plugin) {

        this.plugin = plugin;

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        ItemStack item = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = item.getItemMeta();

        assert meta != null;
        meta.setDisplayName(ChatColor.GREEN + plugin.getConfig().getString("item_name"));
        item.setItemMeta(meta);

        event.getPlayer().getInventory().setItem(4, item);

    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        if (event.getItem() != null && event.getItem().getType() == Material.NETHER_STAR) {

            event.setCancelled(true);

            new ServerSelectionGUI(plugin).openInventory(event.getPlayer());

        }

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.NETHER_STAR) {

            event.setCancelled(true);

        }

    }

}
