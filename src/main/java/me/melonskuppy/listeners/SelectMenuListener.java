package me.melonskuppy.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;

public class SelectMenuListener extends ListenerAdapter {

    @Override
    public void onSelectMenuInteraction(@NotNull SelectMenuInteractionEvent event) {

        List<SelectOption> selectedOptions = event.getSelectedOptions();
        if (selectedOptions.get(0).getValue().equals("commands")) {

            EmbedBuilder commands = new EmbedBuilder()
                    .setTitle("Melon bot help (Page 1/1)")
                    .setDescription("You do not have access to any commands!")
                    .setColor(Color.decode("#2f3136"));
            event.getMessage().editMessageEmbeds(commands.build()).queue();
            event.deferEdit().queue();

        } else if (selectedOptions.get(0).getValue().equals("faq")) {

            EmbedBuilder faq = new EmbedBuilder()
                    .setTitle("Melon bot FAQ")
                    .setDescription("FAQ for the bot")
                    .addField("Who made the bot?", "well isnt it obvious.", false)
                    .addField("invite the bot to my server plz/source code plz!", "no", false)
                    .setColor(Color.decode("#2f3136"));
            event.getMessage().editMessageEmbeds(faq.build()).queue();
            event.deferEdit().queue();

        }

    }
}
