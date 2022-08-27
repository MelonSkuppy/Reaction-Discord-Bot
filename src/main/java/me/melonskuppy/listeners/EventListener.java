package me.melonskuppy.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class EventListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        if (!event.getAuthor().isBot()) {
            String messageSent = event.getMessage().getContentRaw();
            if (event.getMessage().getContentRaw().equals("!invite")) {

                event.getChannel().sendMessage("Invite generated. This invite has a maximum of 1 uses! " + event.getGuild().getTextChannelById(event.getChannel().getId()).createInvite()
                        .setMaxUses(1)
                        .complete()
                        .getUrl()).queue();

            }
        }
    }

}
