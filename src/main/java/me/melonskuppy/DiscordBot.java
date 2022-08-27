package me.melonskuppy;

import me.melonskuppy.listeners.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import org.jetbrains.annotations.NotNull;

import javax.annotation.CheckReturnValue;
import javax.security.auth.login.LoginException;

public class DiscordBot extends ListenerAdapter {

    public static void main(String[] args) throws LoginException, InterruptedException {

        JDA jda = JDABuilder.createDefault("OTcyMTI2MTQ3MTUwMjM3NzA2.GlbVzX.rtvb611AGYjkhRy2kSzPCJ3QIQx2m2UrPWd_jY")
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .setActivity(Activity.watching("reaction roles"))
                .addEventListeners(new EventListener(), new BotCommands(), new ButtonListener(), new SelectMenuListener(), new ContextMenuListener(), new ModalListener())
                .build().awaitReady();

        // Global & Guild commands
        // Global - any server and DMs, takes 15-20 min to register
        // Guild - only in the specified guild but registers instantly

        Guild guild = jda.getGuildById(Values.GUILD.getId());
        if (guild != null) {
            CommandListUpdateAction commands = guild.updateCommands();
            commands.addCommands(
                    Commands.slash("makerr", "make a reaction role message")
                            .addOptions(
                                    new OptionData(OptionType.STRING, "type", "Type of reaction role: colour/pronoun/ping/other", true),
                                    new OptionData(OptionType.CHANNEL, "channel", "Channel to create the reaction role in", true)),
                    Commands.slash("msg", "make the bot send a message in a channel")
                            .addOptions(
                                    new OptionData(OptionType.STRING, "message", "content of the message", true),
                                    new OptionData(OptionType.CHANNEL, "channel", "channel to send the message in", true)),
                    Commands.slash("embed", "send a colourless embed in a channel")
                            .addOptions(
                                    new OptionData(OptionType.STRING, "title", "title of the embed", true),
                                    new OptionData(OptionType.STRING, "description", "description/content of the embed", true),
                                    new OptionData(OptionType.CHANNEL, "channel", "channel to send the message in", true),
                                    new OptionData(OptionType.STRING, "message", "text outside of the embed", false)),
                    Commands.slash("help", "get help for the bot"),
                    Commands.slash("send", "send a message to the staff of the server"),
                    Commands.user("Contact staff"),
                    Commands.slash("suggest", "make a message a suggestion")
                            .addOptions(
                                    new OptionData(OptionType.CHANNEL, "channel", "the channel that the message is in", true),
                                    new OptionData(OptionType.STRING, "message", "the message ID of the message", true)
                            )
            );

            commands.queue();

        }


    }
}
