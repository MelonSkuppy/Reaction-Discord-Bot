package me.melonskuppy.listeners;

import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Modal;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import org.jetbrains.annotations.NotNull;

public class ContextMenuListener extends ListenerAdapter {

    @Override
    public void onUserContextInteraction(@NotNull UserContextInteractionEvent event) {

        TextInput message = TextInput.create("message", "Your message", TextInputStyle.PARAGRAPH)
                .setMinLength(3)
                .setRequired(true)
                .setPlaceholder("What do you want to say/ask?")
                .build();
        Modal modal = Modal.create("message-modal", "Send a message!")
                .addActionRows(ActionRow.of(message))
                .build();
        event.replyModal(modal).queue();


    }
}
