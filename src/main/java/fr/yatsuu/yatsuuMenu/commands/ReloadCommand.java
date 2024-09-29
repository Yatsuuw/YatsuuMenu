package fr.yatsuu.yatsuuMenu.commands;

import fr.yatsuu.yatsuuMenu.YatsuuMenu;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.Objects;

public class ReloadCommand implements CommandExecutor {

    private final YatsuuMenu plugin;

    public ReloadCommand (YatsuuMenu plugin) {

        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {

        if (!sender.hasPermission("yatsuumenu.command.ymload")) {

            String no_perm = Objects.requireNonNull(plugin.getConfig().getString("no_permission")).replace("%permission%", "yatsuumenu.command.ymload");
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', no_perm));

            return true;

        } else {

            if (!Objects.requireNonNull(plugin.getConfig().getConfigurationSection("")).getKeys(false).isEmpty()) {

                plugin.saveDefaultConfig();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("success_reload"))));

            } else {

                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("success_reload"))));

            }

            plugin.reloadConfig();

        }

        return true;

    }

}
