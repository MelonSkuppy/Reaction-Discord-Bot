package me.melonskuppy.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Modal;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.requests.RestAction;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class BotCommands extends ListenerAdapter {

    //Test
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        if (event.getName().equals("makerr")) {

            if (!event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                event.reply("You do not have permission to execute this command!").setEphemeral(true).queue();
                return;
            }

            OptionMapping type = event.getOption("type");
            OptionMapping channel = event.getOption("channel");
            if (type == null || channel == null) {
                event.reply("somehow a reaction role type/reaction role channel was not specified.").setEphemeral(true).queue();
                return;
            }

            String reactionType = type.getAsString();
            long channelID = channel.getAsLong();

            event.reply("Creating a " + reactionType + " reaction role in " + channelID).queue();

            TextChannel rrChannel = event.getJDA().getTextChannelById(channel.getAsLong());

            if (rrChannel == null) {
                event.getHook().sendMessage("An error has occurred while creating the reaction role: An invalid text channel was provided.").queue();
                return;
            }

            switch (reactionType) {
                case "colour" -> {
                    EmbedBuilder colourRoles = new EmbedBuilder()
                            .setTitle("Colour roles");
                    Button colourRed = Button.secondary("colour-red", Emoji.fromFormatted("<:red1:1002859828714151956>"));
                    Button colourPurple = Button.secondary("colour-purple", Emoji.fromFormatted("<:purple2:1002859826814124093>"));
                    Button colourGreen = Button.secondary("colour-green", Emoji.fromFormatted("<:green3:1002859825350328450>"));
                    Button colourPink = Button.secondary("colour-pink", Emoji.fromFormatted("<:pink4:1002859823739715584>"));
                    Button colourOrange = Button.secondary("colour-orange", Emoji.fromFormatted("<:orange5:1002859821961318490>"));
                    Button colourYellow = Button.secondary("colour-yellow", Emoji.fromFormatted("<:yellow6:1002859820258447380>"));
                    Button colourBlue = Button.secondary("colour-blue", Emoji.fromFormatted("<:blue7:1002859818786226277>"));
                    Button colourRemove = Button.danger("colour-remove", "Clear all");
                    Message colourRolesMsg = new MessageBuilder()
                            .setEmbeds(colourRoles.build())
                            .setActionRows(ActionRow.of(colourRed, colourPurple, colourGreen, colourPink), ActionRow.of(colourOrange, colourYellow, colourBlue, colourRemove))
                            .build();
                    rrChannel.sendMessage(colourRolesMsg).queue();
                }
                case "pronoun" -> {
                    EmbedBuilder pronounRoles = new EmbedBuilder()
                            .setTitle("Pronoun roles");
                    Button pronounHeHim = Button.secondary("pronoun-he-him", "He/Him")
                            .withEmoji(Emoji.fromFormatted("<:pronoun1:1002923183399243787>"));
                    Button pronounSheHer = Button.secondary("pronoun-she-her", "She/Her")
                            .withEmoji(Emoji.fromFormatted("<:pronoun2:1002923181000101891>"));
                    Button pronounHeThey = Button.secondary("pronoun-he-they", "He/They")
                            .withEmoji(Emoji.fromFormatted("<:pronoun1:1002923183399243787>"));
                    Button pronounSheThey = Button.secondary("pronoun-she-they", "She/They")
                            .withEmoji(Emoji.fromFormatted("<:pronoun2:1002923181000101891>"));
                    Button pronounTheyThem = Button.secondary("pronoun-they-them", "They/Them")
                            .withEmoji(Emoji.fromFormatted("<:pronoun3:1002923179561455696>"));
                    Button pronounAny = Button.secondary("pronoun-any", "Any pronouns")
                            .withEmoji(Emoji.fromFormatted("<:pronoun3:1002923179561455696>"));
                    Button pronounRemove = Button.danger("pronoun-remove", "Clear all");
                    Message pronounRolesMsg = new MessageBuilder()
                            .setEmbeds(pronounRoles.build())
                            .setActionRows(ActionRow.of(pronounHeHim, pronounSheHer, pronounHeThey, pronounSheThey), ActionRow.of(pronounTheyThem, pronounAny, pronounRemove))
                            .build();
                    rrChannel.sendMessage(pronounRolesMsg).queue();
                }
                case "ping" -> {
                    EmbedBuilder pingRoles = new EmbedBuilder()
                            .setTitle("Ping roles");
                    Button pingAnnouncements = Button.secondary("ping-announcements", "Announcements")
                            .withEmoji(Emoji.fromUnicode("U+1F4E3"));
                    Button pingGiveaways = Button.secondary("ping-giveaways", "Giveaways")
                            .withEmoji(Emoji.fromUnicode("U+1F389"));
                    Button pingRemove = Button.danger("ping-remove", "Clear all");
                    Message pingRolesMsg = new MessageBuilder()
                            .setEmbeds(pingRoles.build())
                            .setActionRows(ActionRow.of(pingAnnouncements, pingGiveaways, pingRemove))
                            .build();
                    rrChannel.sendMessage(pingRolesMsg).queue();
                }
                case "other" -> {
                    EmbedBuilder otherRoles = new EmbedBuilder()
                            .setTitle("Other roles");
                    Button otherDankAccess = Button.secondary("other-dank-access", "Dank Grinding access")
                            .withEmoji(Emoji.fromFormatted("<:dank:1003101972955660328>"));
                    Button otherRemove = Button.danger("other-remove", "Clear all");
                    Message otherRolesMsg = new MessageBuilder()
                            .setEmbeds(otherRoles.build())
                            .setActionRows(ActionRow.of(otherDankAccess, otherRemove))
                            .build();
                    rrChannel.sendMessage(otherRolesMsg).queue();
                }
                default ->
                        event.getHook().sendMessage("An error has occurred while creating the reaction role: Invalid reaction type! Reaction types: `COLOUR`, `PRONOUN`, `PING`").queue();
            }


        } else if (event.getName().equals("msg")) {

            if (!event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                event.reply("You do not have permission to execute this command!").setEphemeral(true).queue();
                return;
            }

            OptionMapping message = event.getOption("message");
            OptionMapping channel = event.getOption("channel");
            if (message == null || channel == null) {
                event.reply("somehow a message/channel was not specified.").setEphemeral(true).queue();
                return;
            }

            event.reply("Sent \"" + message.getAsString() + "\" in " + channel.getAsChannel().getName() + "!").setEphemeral(true).queue();

            TextChannel messageChannel = event.getJDA().getTextChannelById(channel.getAsLong());

            if (messageChannel == null) {
                event.getHook().sendMessage("An error has occurred while sending the message: An invalid text channel was provided.").queue();
                return;
            }
            Message content = new MessageBuilder()
                    .append(message.getAsString())
                    .build();
            messageChannel.sendMessage(content).queue();

        } else if (event.getName().equals("embed")) {

            if (!event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                event.reply("You do not have permission to execute this command!").setEphemeral(true).queue();
                return;
            }

            OptionMapping message = event.getOption("message");
            OptionMapping title = event.getOption("title");
            OptionMapping description = event.getOption("description");
            OptionMapping channel = event.getOption("channel");
            if (title == null || description == null || channel == null) {
                event.reply("somehow a message/channel was not specified.").setEphemeral(true).queue();
                return;
            }

            event.reply("Sent \"" + description.getAsString() + "\" as a embed in " + channel.getAsChannel().getName() + "!").setEphemeral(true).queue();

            TextChannel messageChannel = event.getJDA().getTextChannelById(channel.getAsLong());

            if (messageChannel == null) {
                event.getHook().sendMessage("An error has occurred while sending the message: An invalid text channel was provided.").queue();
                return;
            }
            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle(title.getAsString())
                    .setDescription(description.getAsString())
                    .setColor(Color.decode("#2f3136"));
            Message content;
            if (message != null) {
                content = new MessageBuilder()
                        .append(message.getAsString())
                        .setEmbeds(embed.build())
                        .build();
            } else {
                content = new MessageBuilder()
                        .setEmbeds(embed.build())
                        .build();
            }
            messageChannel.sendMessage(content).queue();
        } else if (event.getName().equals("help")) {

            Button firstPage = Button.secondary("firstpage", Emoji.fromUnicode("U+23EA"));
            Button previousPage = Button.secondary("previouspage", Emoji.fromUnicode("U+2B05"));
            Button nextPage = Button.secondary("nextpage", Emoji.fromUnicode("U+27A1"));
            Button lastPage = Button.secondary("lastpage", Emoji.fromUnicode("U+23E9"));
            SelectMenu selectMenu = SelectMenu.create("menu:help")
                    .setPlaceholder("Select a menu...")
                    .setRequiredRange(1, 1)
                    .addOptions(SelectOption.of("Commands", "commands"), SelectOption.of("FAQ", "faq"))
                    .build();
            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("Melon bot help (Page 1/1)")
                    .setDescription("You do not have access to any commands!")
                    .setColor(Color.decode("#2f3136"));
            Message content = new MessageBuilder()
                    .setEmbeds(embed.build())
                    .setActionRows(ActionRow.of(firstPage.asDisabled(), previousPage.asDisabled(), nextPage.asDisabled(), lastPage.asDisabled()), ActionRow.of(selectMenu))
                    .build();
            event.reply(content).queue();
            event.getHook().editOriginalComponents(ActionRow.of(firstPage.asDisabled(), previousPage.asDisabled(), nextPage.asDisabled(), lastPage.asDisabled()), ActionRow.of(selectMenu.asDisabled())).queueAfter(20, TimeUnit.SECONDS);

        } else if (event.getName().equals("send")) {

            TextInput message = TextInput.create("message", "Your message", TextInputStyle.PARAGRAPH)
                    .setMinLength(3)
                    .setRequired(true)
                    .setPlaceholder("What do you want to say/ask?")
                    .build();
            Modal modal = Modal.create("message-modal", "Send a message!")
                    .addActionRows(ActionRow.of(message))
                    .build();
            event.replyModal(modal).queue();

        } else if (event.getName().equals("suggest")) {

            if (!event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                event.reply("You do not have permission to execute this command!").setEphemeral(true).queue();
                return;
            }

            OptionMapping channel = event.getOption("channel");
            OptionMapping id = event.getOption("message");
            if (channel == null || id == null) {
                event.reply("somehow a message ID was not specified.").setEphemeral(true).queue();
                return;
            }
            TextChannel textChannel = event.getGuild().getTextChannelById(channel.getAsLong());
            if (textChannel == null) {
                event.reply("An invalid text channel was provided!").setEphemeral(true).queue();
            }
            RestAction<Message> restAction = textChannel.retrieveMessageById(id.getAsLong());
            restAction.queue(message -> {
                message.addReaction(Emoji.fromUnicode("U+1F44D")).queue();
                message.addReaction(Emoji.fromUnicode("U+1F44E")).queue();
                    });
            event.reply("Reactions added!").setEphemeral(true).queue();

        }

    }
}
