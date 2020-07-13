package red.felnull.ikisugibot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import red.felnull.ikisugibot.command.IKSGCommands;
import red.felnull.ikisugibot.handler.MessageHandler;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static JFrame frame;

    public static void main(String[] args) {
        init();
        System.out.println(OptionConfig.TOKEN);
        DiscordClient client = DiscordClient.create(OptionConfig.TOKEN);
        GatewayDiscordClient gateway = client.login().block();
        gateway.on(MessageCreateEvent.class).subscribe(e -> {

            if (e.getMessage().getAuthor().get().isBot())
                return;


            MessageHandler.onMessageCreate(e);
        });
        gateway.onDisconnect().block();

    }

    public static void init() {
        OptionConfig.init();
        IKSGCommands.init();
        frame = new JFrame("Ikisugi Bot");
        frame.setSize(305, 46);
        Toolkit kit = Toolkit.getDefaultToolkit();
        double h = kit.getScreenSize().getHeight();
        double w = kit.getScreenSize().getWidth();
        frame.setLocation((int) (w / 2 - frame.getSize().getWidth() / 2), (int) (h / 2 - frame.getSize().getHeight() / 2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel jl = new JLabel("終了するときはこのウィンドウを閉じてください。");
        frame.add(jl, BorderLayout.NORTH);
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
