package me.melonskuppy.listeners;

import me.melonskuppy.Values;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ButtonListener extends ListenerAdapter {

    int i = -1;
    int j = -1;
    int x = -1;
    int y = -1;

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {

        Guild guild = event.getGuild();
        List<Role> allColours = new LinkedList<>();
        allColours.add(guild.getRoleById(Values.RED.getId()));
        allColours.add(guild.getRoleById(Values.PURPLE.getId()));
        allColours.add(guild.getRoleById(Values.GREEN.getId()));
        allColours.add(guild.getRoleById(Values.PINK.getId()));
        allColours.add(guild.getRoleById(Values.ORANGE.getId()));
        allColours.add(guild.getRoleById(Values.YELLOW.getId()));
        allColours.add(guild.getRoleById(Values.BLUE.getId()));

        List<Role> allPronouns = new LinkedList<>();
        allPronouns.add(guild.getRoleById(Values.HE_HIM.getId()));
        allPronouns.add(guild.getRoleById(Values.SHE_HER.getId()));
        allPronouns.add(guild.getRoleById(Values.HE_THEY.getId()));
        allPronouns.add(guild.getRoleById(Values.SHE_THEY.getId()));
        allPronouns.add(guild.getRoleById(Values.THEY_THEM.getId()));
        allPronouns.add(guild.getRoleById(Values.ANY.getId()));

        List<Role> allPings = new LinkedList<>();
        allPings.add(guild.getRoleById(Values.ANNOUNCEMENTS.getId()));
        allPings.add(guild.getRoleById(Values.GIVEAWAYS.getId()));

        List<Role> allOthers = new LinkedList<>();
        allOthers.add(guild.getRoleById(Values.DANK_ACCESS.getId()));

        switch (Objects.requireNonNull(event.getButton().getId())) {
            case "colour-red" -> i = 0;
            case "colour-purple" -> i = 1;
            case "colour-green" -> i = 2;
            case "colour-pink" -> i = 3;
            case "colour-orange" -> i = 4;
            case "colour-yellow" -> i = 5;
            case "colour-blue" -> i = 6;
            case "colour-remove" -> i = 7;

            case "pronoun-he-him" -> j = 0;
            case "pronoun-she-her" -> j = 1;
            case "pronoun-he-they" -> j = 2;
            case "pronoun-she-they" -> j = 3;
            case "pronoun-they-them" -> j = 4;
            case "pronoun-any" -> j = 5;
            case "pronoun-remove" -> j = 6;

            case "ping-announcements" -> x = 0;
            case "ping-giveaways" -> x = 1;
            case "ping-remove" -> x = 2;

            case "other-dank-access" -> y = 0;
            case "other-remove" -> y = 1;
            default -> event.reply("Received interaction event. (There is no response for this button)").setEphemeral(true).queue();
        }
        if (i != -1) {
            if (i == 7) {
                event.reply("Cleared all colour roles!").setEphemeral(true).queue();
                Objects.requireNonNull(event.getGuild()).modifyMemberRoles(event.getMember(), null, allColours).reason("Reaction role").queue();
                i = -1;
                return;
            }
            List<Role> roles = Objects.requireNonNull(event.getMember()).getRoles();
            List<Role> colour = new LinkedList<>();
            colour.add(allColours.get(i));
            if (new HashSet<>(roles).containsAll(colour)) {
                event.reply("Removed " + allColours.get(i).getName() + " from you and cleared all other roles!").setEphemeral(true).queue();
                Objects.requireNonNull(event.getGuild()).modifyMemberRoles(event.getMember(), null, allColours).reason("Reaction role").queue();
                i = -1;
                return;
            }
            event.reply("Gave you " + allColours.get(i).getName() + " and cleared all other roles!").setEphemeral(true).queue();
            allColours.remove(i);
            Objects.requireNonNull(event.getGuild()).modifyMemberRoles(event.getMember(), colour, allColours).reason("Reaction role").queue();
            i = -1;

        } else if (j != -1) {
            if (j == 6) {
                event.reply("Cleared all pronoun roles!").setEphemeral(true).queue();
                Objects.requireNonNull(event.getGuild()).modifyMemberRoles(event.getMember(), null, allPronouns).reason("Reaction role").queue();
                j = -1;
                return;
            }
            List<Role> roles = Objects.requireNonNull(event.getMember()).getRoles();
            List<Role> pronoun = new LinkedList<>();
            pronoun.add(allPronouns.get(j));
            if (new HashSet<>(roles).containsAll(pronoun)) {
                event.reply("Removed " + allPronouns.get(j).getName() + " from you and cleared all other roles!").setEphemeral(true).queue();
                Objects.requireNonNull(event.getGuild()).modifyMemberRoles(event.getMember(), null, allPronouns).reason("Reaction role").queue();
                j = -1;
                return;
            }
            event.reply("Gave you " + allPronouns.get(j).getName() + " and cleared all other roles!").setEphemeral(true).queue();
            allPronouns.remove(j);
            Objects.requireNonNull(event.getGuild()).modifyMemberRoles(event.getMember(), pronoun, allPronouns).reason("Reaction role").queue();
            j = -1;
        } else if (x != -1) {
            if (x == 2) {
                event.reply("Cleared all ping roles!").setEphemeral(true).queue();
                Objects.requireNonNull(event.getGuild()).modifyMemberRoles(event.getMember(), null, allPings).reason("Reaction role").queue();
                x = -1;
                return;
            }
            List<Role> roles = Objects.requireNonNull(event.getMember()).getRoles();
            List<Role> ping = new LinkedList<>();
            ping.add(allPings.get(x));
            if (new HashSet<>(roles).containsAll(ping)) {
                event.reply("Removed " + allPings.get(x).getName() + " from you!").setEphemeral(true).queue();
                Objects.requireNonNull(event.getGuild()).modifyMemberRoles(event.getMember(), null, ping).reason("Reaction role").queue();
                x = -1;
                return;
            }
            event.reply("Gave you " + allPings.get(x).getName() + "!").setEphemeral(true).queue();
            allPings.remove(x);
            Objects.requireNonNull(event.getGuild()).modifyMemberRoles(event.getMember(), ping, null).reason("Reaction role").queue();
            x = -1;

        } else if (y != -1) {
            if (y == 1) {
                event.reply("Cleared all other roles!").setEphemeral(true).queue();
                Objects.requireNonNull(event.getGuild()).modifyMemberRoles(event.getMember(), null, allOthers).reason("Reaction role").queue();
                y = -1;
                return;
            }
            List<Role> roles = Objects.requireNonNull(event.getMember()).getRoles();
            List<Role> other = new LinkedList<>();
            other.add(allOthers.get(y));
            if (new HashSet<>(roles).containsAll(other)) {
                event.reply("Removed " + allOthers.get(y).getName() + " from you!").setEphemeral(true).queue();
                Objects.requireNonNull(event.getGuild()).modifyMemberRoles(event.getMember(), null, other).reason("Reaction role").queue();
                y = -1;
                return;
            }
            event.reply("Gave you " + allOthers.get(y).getName() + "!").setEphemeral(true).queue();
            allOthers.remove(y);
            Objects.requireNonNull(event.getGuild()).modifyMemberRoles(event.getMember(), other, null).reason("Reaction role").queue();
            y = -1;

        }
    }

}
