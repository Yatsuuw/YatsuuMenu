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
    public boolean onCommand( @Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {

        if (!sender.hasPermission("yatsuumenu.command.reload")) {

            String no_perm = Objects.requireNonNull(plugin.getConfig().getString("no_permission")).replace("%permission%", "yatsuumenu.command.reload");
            sender.sendMessage(ChatColor.RED + no_perm);

        } else {

            if (!sender.hasPermission("yatsuumenu.command.reload")) {

                String no_perm = Objects.requireNonNull(plugin.getConfig().getString("no_permission")).replace("%permission%", "yatsuumenu.command.reload");
                sender.sendMessage(ChatColor.RED + no_perm);

                return true;

            }

            plugin.reloadConfig();
            sender.sendMessage(ChatColor.GREEN + plugin.getConfig().getString("success_reload"));

        }

        return true;

    }

}
