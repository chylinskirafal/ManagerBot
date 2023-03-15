package pl.chylu.listeners;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;
import pl.chylu.Main;

import java.util.ArrayList;
import java.util.List;

public class EventListener  extends ListenerAdapter {
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        User user = event.getUser();
        String emoji = event.getReaction().getEmoji().getAsReactionCode();
        String channelMention = event.getChannel().getAsMention();
        String message = event.getUser().getAsMention() + " zareagował na " + emoji + " na kanale " + channelMention;
        event.getGuild().getTextChannelById(event.getChannel().getId()).sendMessage(message).queue();
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw().toLowerCase();

        if (message.contains(Main.prefix)) {
            try {
                if (message.contains("cord")) {
                    event.getChannel().sendMessage(event.getAuthor().getAsMention() + " W sprawie Corda daj spokój. Nie przekonasz go do niczego.").queue();
                }
                if (message.contains("chylu")) {
                    event.getChannel().sendMessage(event.getAuthor().getAsMention() + " Chylu to mój autor. Zacny człowiek i asteta.").queue();
                }

            } catch (Exception e) {
                System.out.println("Error: Problem z odczytem wiadomości od użytkownika.");
            }
        }
    }
}