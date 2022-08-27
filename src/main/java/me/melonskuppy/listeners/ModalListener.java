package me.melonskuppy.listeners;

import me.melonskuppy.Values;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;


public class ModalListener extends ListenerAdapter {

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        if (event.getModalId().equals("message-modal")) {

            String message = event.getValue("message").getAsString();

            EmbedBuilder embed = new EmbedBuilder()
                    .setAuthor(event.getUser().getAsTag(), null, event.getUser().getAvatarUrl())
                    .setTitle("A message has been received!")
                    .setDescription(message)
                    .setFooter("This message was written by the author of the message, and not the bot!")
                    .setColor(Color.decode("#00ffc4"));
            Message content = new MessageBuilder()
                    .setEmbeds(embed.build())
                    .build();
            TextChannel channel = event.getGuild().getTextChannelById(Values.MESSAGE_CHANNEL.getId());
            if (channel == null) {
                event.reply("The bot was unable to send your message: the channel is invalid!").queue();
                return;
            }
            channel.sendMessage(content).queue();
            event.reply("Your message has been sent!").setEphemeral(true).queue();

        }
    }
}
