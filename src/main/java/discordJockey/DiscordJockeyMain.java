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
	static EmbedBuilder cbt = new EmbedBuilder()
			.setTitle("Cock And Ball Torture")
			.setAuthor("Why I shouldn't be allowed to use a computer")
			.setDescription("Cock and ball torture (CBT), penis torture or dick torture is a sexual activity involving application of pain or constriction to the penis or testicles. This may involve directly painful activities, such as genital piercing, wax play, genital spanking, squeezing, ball-busting, genital flogging, urethral play, tickle torture, erotic electrostimulation or even kicking.[1] The recipient of such activities may receive direct physical pleasure via masochism, or emotional pleasure through erotic humiliation, or knowledge that the play is pleasing to a sadistic dominant. Many of these practices carry significant health risks.[2]")
			.addField("Devices", "Similar to many other sexual activities, CBT can be performed using toys and devices to make the penis and testicles more easily accessible for attack, or for foreplay purposes.[12][13]")
			.addInlineField("Ball Stretcher", "A ball stretcher is a sex toy that is used to elongate the scrotum and provide a feeling of weight pulling the testicles away from the body. This can be particularly enjoyable for the wearer as it can make an orgasm more intense, as testicles are prevented from moving up. Intended to make one's testicles permanently hang much lower than before (if used regularly for extended periods of time), this sex toy can be potentially harmful to the male genitals as the circulation of blood can be easily cut off if over-tightened. While leather stretchers are most common, other models consist of an assortment of steel rings that fastens with screws, causing additional but only mildly uncomfortable weight to the wearer's testicles. The length of the stretcher may vary from 1-4 inches.[14] A more dangerous type of ball stretcher can be home-made simply by wrapping rope or string around one's scrotum until it is eventually stretched to the desired length.")		
			.addInlineField("Ball Crusher", "A ball crusher is a device made from either metal or often clear acrylic that squeezes the testicles slowly by turning a nut or screw. How tight it is clamped depends on the pain tolerance of the person it is used on. A ball crusher is often combined with bondage, either with a partner or by oneself.");
	
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
						case "ruinserver bonerific":
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
