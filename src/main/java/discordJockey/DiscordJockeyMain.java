package discordJockey;
import org.javacord.api.*;
import org.javacord.api.entity.*;
import org.javacord.api.entity.user.*;
import org.javacord.api.entity.permission.*;
import org.javacord.api.entity.channel.*;
import org.javacord.api.entity.message.*;
import org.javacord.api.entity.message.embed.Embed;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.*;
import org.javacord.api.event.server.role.UserRoleAddEvent;

import java.util.Iterator;
import java.util.Optional;
import java.util.Optional.*;
import java.util.Scanner;

public class DiscordJockeyMain {
	static TextChannel mainChannel = null;
	static boolean ruinServer = false;
	
	//Creates Embed for CBT module
	//Removed from public facing github due to crude humor
	static EmbedBuilder cbt = new EmbedBuilder()
			.setTitle(" ")
			.setAuthor(" ")
			.setDescription(" ")
			.addField(" ", " ")
			.addInlineField(" ", " ")		
			.addInlineField(" ", " ");
	
	//Token removed from public facing github
	public static void main(String[] args) {
		System.out.println("Hello World");
		DiscordApi api = new DiscordApiBuilder().setToken("").login().join();
		System.out.println("*** Connection Active *** Begin Bot Boot ***");
		//Handles bot boot and logging in
		
		//Prints information to console about bot boot
		System.out.println("\n --- Beginning boot report --- \n Currently active servers: ");
		System.out.println(api.getServers());
		System.out.println("--- Bot Boot Complete --- \n \n \n");
		
		//Creates module to handle role modification in a server
		ServerUpdater roleManager = new ServerUpdater(null);
		
		
				
				//Command reading
		api.addMessageCreateListener(event -> {
			Message message = event.getMessage();
			if (message.getReadableContent().length() > 0) {
				if (message.getReadableContent().charAt(0) == '$') {
					if(message.getContent().length() > 1) {
						MessageBuilder text = new MessageBuilder();
						String contents = message.getContent().substring(1);
						contents = contents.toLowerCase();
						switch(contents) {
						case "help":
							text.setContent("$help - Displays a this messsage \n $[color] - Gives you a color \n $[rolename] - Adds you to role");
							text.send(message.getChannel());
							break;
						case "settextchannel":
							text.setContent("Text Channel Set");
							setMainChannel(message.getChannel());
							text.send(message.getChannel());
							break;
						case "cbt":
							if (message.getAuthor().getIdAsString().contentEquals("115852711596916740")) {
								event.getChannel().sendMessage(cbt);
								System.out.println("CBT");
							} else {
								event.getChannel().sendMessage("You are not permitted to use this command");
							}
							break;
						case "loaddb":
							try {
								Library lib = new Library("C:\\Users\\Dave\\Documents\\musicdb.txt");
								event.getChannel().sendMessage("Music db load successful");
								System.out.println(lib.getSong(0, 0, 0).toString());
							} catch(Exception e) {
								event.getChannel().sendMessage("Music db load has failed, please see console for more info");
								System.out.print(e.getClass());
							}
							break;
						case "ruinserver":
							event.getChannel().sendMessage("Syntax: $ruinserver [password]");
							event.getChannel().sendMessage("Ruins the server.");
							break;
						case "ruinserver 12345":
							if(ruinServer == false) {
								event.getChannel().sendMessage("Server Ruiner Activated");
								ruinServer = true;
								break;
							}
							if(ruinServer == true) {
								event.getChannel().sendMessage("Server Ruiner Deactivated");
								ruinServer = false;
								break;
							}
						case "userlist":
							Server UserServer = event.getServer().get();
							Iterator<User> userlist = UserServer.getMembers().iterator();
							while(userlist.hasNext()) {
								System.out.println(userlist.next().getName());
							}
							break;
						case "savedb":
							try {
								Library lib = new Library(10, 10, 10); 
								System.out.println(lib.addSong(0, 0, "testsong", "www.google.com", 60, false, false, true));
								System.out.println(lib.addSong(3, 2, "testsong2", "www.bing.com", 42, true, true, false));
								lib.saveLib();
								event.getChannel().sendMessage("Music db saved successfully");
							}catch(Exception e) {
								event.getChannel().sendMessage("Music db save has failed, see console for more info");
								System.out.println(e.getMessage());
								System.out.println(e.getCause());
								System.out.println(e.getClass());
								System.out.println(e.getStackTrace());
							}
						/* case "red":
							message.getServer().get().addRoleToUser(message.getUserAuthor().get(), roleHandler(message, "Red"));
							break;
						case "blue":
							message.getServer().get().addRoleToUser(message.getUserAuthor().get(), roleHandler(message, "Blue"));
							break;
						case "green":
							message.getServer().get().addRoleToUser(message.getUserAuthor().get(), roleHandler(message, "Green"));	
							break;
						case "indigo":
							message.getServer().get().addRoleToUser(message.getUserAuthor().get(), roleHandler(message, "Indigo"));
							break;
						case "orange":
							message.getServer().get().addRoleToUser(message.getUserAuthor().get(), roleHandler(message, "Orange"));
							break;
						case "teal":
							text.setContent("Not implemented yet - teal");
							text.send(message.getChannel());
							break;
						case "violet":
							message.getServer().get().addRoleToUser(message.getUserAuthor().get(), roleHandler(message, "Violet"));
							break;
						case "yellow":
							message.getServer().get().addRoleToUser(message.getUserAuthor().get(), roleHandler(message, "Yellow"));
							break;
						case "forhonorer":
							message.getServer().get().addRoleToUser(message.getUserAuthor().get(), roleHandler(message, "ForHonorer"));
							break;
						case "stellaris":
							message.getServer().get().addRoleToUser(message.getUserAuthor().get(), roleHandler(message, "Stellaris"));
							break;
						case "hoi":
							message.getServer().get().addRoleToUser(message.getUserAuthor().get(), roleHandler(message, "HoI"));
							break;
						case "the crew":
							message.getServer().get().addRoleToUser(message.getUserAuthor().get(), roleHandler(message, "The Crew"));
							break;
						case "roletest":
							Server currentServer = message.getServer().get();
							Object[] roles = currentServer.getRolesByName("Red").toArray();
							for(int i = 0; i < roles.length; i ++) {
								System.out.println(roles[i].toString());
							}
							 */
						default:
							text.setContent("Unrecognized command");
							text.send(message.getChannel());
							break;
						}					
					}
				} else if(ruinServer == true) {
					String buffer = message.getContent();
					buffer = buffer.replaceAll("l", "w");
					buffer = buffer.replaceAll("o", "owo");
					int r = 0;
					r = (int) (Math.random()*100);
					if((r >= 50) && (r < 70)) {
						buffer = buffer.concat(" ^w^ ");
					}
					if((r >= 70) && (r < 80)) {
						buffer = buffer.concat(" >:3");
					}
					if((r >=80) && (r < 100)) {
						buffer = buffer.concat(" *nuzzles*");
					}
					EmbedBuilder newMessage = new EmbedBuilder()
							.setAuthor(message.getAuthor())
							.setDescription(buffer);
					TextChannel channel = message.getChannel();
					message.delete();
					channel.sendMessage(newMessage);
				}
			}
		});
		
		
		
		//Dumb memes
		api.addMessageCreateListener(event -> {
		    Message message = event.getMessage();
		    if (message.getContent().equalsIgnoreCase("Orange Man Bad")) {
		      event.getChannel().sendMessage(":large_orange_diamond: :man: :thumbsdown: ");
		    }
		    else if (message.getContent().equalsIgnoreCase("Orange Man Good")) {
		    	event.getChannel().sendMessage(":large_orange_diamond: :man: :thumbsup: ");
		    }
		    else if (message.getContent().equalsIgnoreCase("Orange Man ok")) {
		    	event.getChannel().sendMessage(":large_orange_diamond: :man: :ok:");
		    }
		});
		
		
		
		
		
		//Prints from console to chosen text channel
		System.out.println("Microphone activated: ");
		while (true) {
		Scanner keyboard = new Scanner(System.in);
		String output = keyboard.nextLine();
		MessageBuilder text = new MessageBuilder();
		text.setContent(output);
		if (mainChannel == null) {
			System.out.println("Set a text channel first dingus");
		} else {
			text.send(mainChannel);
		}
		
		}

	}
		//Function to return text channel hopefully
		public static void setMainChannel(TextChannel t){
			mainChannel = t;
		}
		
		//Function to handle assigning roles
		public static Role roleHandler(Message input, String roleName) {
			Server currentServer = input.getServer().get();
			Object[] roles = currentServer.getRolesByName(roleName).toArray();
			return (Role) roles[0];
		}
}
