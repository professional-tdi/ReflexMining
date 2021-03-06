package world.ntdi.rm.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import world.ntdi.rm.Commands.Commands.ExplodeCMD;
import world.ntdi.rm.Commands.Commands.StartCMD;
import world.ntdi.rm.RM;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandManager implements CommandExecutor {
    private final ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandManager(){
        subCommands.add(new ExplodeCMD());
        subCommands.add(new StartCMD());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0){
            for (int i = 0; i < getSubcommands().size(); i++){
                if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())){
                    ArrayList<String> newArgs = new ArrayList<>(Arrays.asList(args));
                    newArgs.remove(0);
                    String[] newArgsArray = newArgs.toArray(new String[0]);
                    getSubcommands().get(i).perform(sender, newArgsArray);
                }
            }
        }else {
            sender.sendMessage(ChatColor.DARK_GRAY + "----------------" + RM.logo + ChatColor.DARK_GRAY +"----------------"); sender.sendMessage();
            for (int i = 0; i < getSubcommands().size(); i++){
                SubCommand sub = subCommands.get(i);
                String synt = ChatColor.BLUE + sub.getSyntax(); String recolored = synt.replaceFirst("/", ChatColor.DARK_GRAY + "/" + ChatColor.BLUE);
                String desc = ChatColor.AQUA + sub.getDescription();
                String sep = ChatColor.DARK_GRAY + " - ";

                sender.sendMessage(recolored + sep + desc);
            }
            sender.sendMessage(); sender.sendMessage(ChatColor.DARK_GRAY + "---------------------------------------------");
        } return true;
    }

    public ArrayList<SubCommand> getSubcommands(){ return subCommands; }
}
