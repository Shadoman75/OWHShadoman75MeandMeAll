package info.insomniax.shadoman75.bukkit;


import info.insomniax.shadoman75.bukkit.permissions.PermissionsHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

public final class MePlugin extends JavaPlugin {
	
	public void onDisable(){
		getLogger().info("Disabled... for now");
	}
	public void onEnable(){
		getLogger().info("Enabled... for now");
		permissions = new PermissionsHandler(this);
		if(permissions.setupPermissions()){
			getLogger().info("Setting Up Permissions");
	
		} else {
			getLogger().info("failed to setup Permissions");
			this.getServer().getPluginManager().disablePlugin(this);
		}
	}
	public PermissionsHandler permissions = null;
	
	public boolean onCommand(CommandSender sender, Command cmd,
		String commandLabel, String[] args) {
		
		// if someone just happened to type /me and had a message after it...
		if(cmd.getName().equalsIgnoreCase("me")){
			if(permissions.has(sender, "meplugin.me")){
			
				if(args.length > 0){
					// ARGUMENT LOOP START!
					StringBuilder buffer = new StringBuilder();

					for(int i = 0; i < args.length; i++)
					{
						buffer.append(' ').append(args[i]);
					}
					// argument loop end...
				
					// PLAYER LOOPZ!
					for(Player p:((Player)sender).getWorld().getPlayers()){
						// and the message will be sent here :o
						p.sendMessage(ChatColor.AQUA + "~" + " " + sender.getName() + buffer.toString());
						
					}
					return true;
				} 
			}
		}
		return false;
	}
	public boolean onCommand1(CommandSender sender, Command cmd,
		String commandLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("meall")){
			if(permissions.has(sender, "meplugin.all")){
				if(args.length > 0){
					// ARGUMENT LOOP START!
					StringBuilder buffer2 = new StringBuilder();

					for(int i = 0; i < args.length; i++)
					{
						buffer2.append(' ').append(args[i]);
					}
					// argument loop end...
					for(Player playr: getServer().getOnlinePlayers()){
						playr.chat("/me" + buffer2.toString());
					}
					return true;
				}
			}
		}
		return false;
	}

}