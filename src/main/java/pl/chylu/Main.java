package pl.chylu;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import pl.chylu.listeners.EventListener;
import pl.chylu.listeners.SlashCommands;

import javax.security.auth.login.LoginException;

public class Main {

    private final Dotenv config;
    private final ShardManager shardManager;
    public static String prefix = "$";

    public Main() throws LoginException {
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");
        //prefix = config.get("PREFIX");

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing("zgłoszenia wyCHYLenia"));
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
                GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MODERATION,
                GatewayIntent.DIRECT_MESSAGE_TYPING, GatewayIntent.GUILD_MEMBERS);
        shardManager = builder.build();

        shardManager.addEventListener(new EventListener(), new SlashCommands());
    }

    public Dotenv getConfig() {
        return config;
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public static void main(String[] args) {
        try {
            Main bot = new Main();
        } catch (LoginException e) {
            System.out.println("Error: Proviceded bot token is invalid");
        }
    }
}