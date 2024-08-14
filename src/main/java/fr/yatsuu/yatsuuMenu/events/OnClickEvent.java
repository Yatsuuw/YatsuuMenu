package fr.yatsuu.yatsuuMenu.events;

import fr.yatsuu.yatsuuMenu.YatsuuMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class OnClickEvent implements Listener {
    YatsuuMenu plugin;
    public OnClickEvent(YatsuuMenu plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        String viewTitle = event.getView().getTitle();

        if (viewTitle.equals(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("interface_name")))) {
            event.setCancelled(true);
            event.setResult(Event.Result.DENY);

            if (item != null && item.getType() == Material.GRASS_BLOCK) {
                if (event.isLeftClick() || event.isRightClick()) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("switching_message")));
                    player.closeInventory();
                    sendPlayerToServer(player, plugin.getConfig().getString("target_server"));
                }
            }
        }
    }

    private void sendPlayerToServer(Player player, String server) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
    }
}