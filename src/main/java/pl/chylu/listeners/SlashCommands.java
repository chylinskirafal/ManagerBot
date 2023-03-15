package pl.chylu.listeners;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SlashCommands extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (command.equals("cord")) {
            String userTag = event.getUser().getAsTag();
            event.reply( "W sprawie Corda daj spokój. Nie przekonasz go do niczego. " + event.getUser().getAsMention() + " Wyjaśniłem?").queue();
        }
    }

    @Override
    public void onGuildReady (@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("cord", "Serio chcesz pogadać o Cordzie?"));
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}
