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
	
	public String arrayToString(String[] args){
		if(args.length > 0){
			// ARGUMENT LOOP START!
			StringBuilder buffer = new StringBuilder();
			for(int i = 0; i < args.length; i++){
				buffer.append(' ').append(args[i]);
			}
			// argument loop end...
			return buffer.toString();
		}
		return null;
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd,
		String commandLabel, String[] args) {
		String message = arrayToString(args);
		// if someone just happened to type /me and had a message after it...
		if(cmd.getName().equalsIgnoreCase("me")){
			if(permissions.has(sender, "meplugin.me")){
				if(message != null){//placeholder for now
					// PLAYER LOOPZ!
					for(Player p:((Player)sender).getWorld().getPlayers()){
						// and the message will be sent here :o
						p.sendMessage(ChatColor.AQUA + "~" + " " + sender.getName() + message);
					}
					return true;
				}
			}
		} else {
		if(cmd.getName().equalsIgnoreCase("meall")){
			if(permissions.has(sender, "meplugin.all")){
				if(message != null){ //placeholder for now
					for(Player playr: getServer().getOnlinePlayers()){
						playr.chat("/me" + message);
					}
					return true;
				}
			}
		}}
		return false;
	}

}