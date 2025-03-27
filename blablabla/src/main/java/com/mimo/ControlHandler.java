package com.mimo;

import org.jline.terminal.TerminalBuilder;

public class ControlHandler {
    public static void handleKey() {
        try {
            var terminal = TerminalBuilder.terminal();
            terminal.enterRawMode();
            while (Main.isRunning) {
                int key = terminal.reader().read();
                int oldX = Main.getPlayer().getX();
                int oldY = Main.getPlayer().getY();

                switch (key) {
                    case 'w' -> Main.getPlayer().move(oldX, oldY - 1);
                    case 'a' -> Main.getPlayer().move(oldX - 1, oldY);
                    case 's' -> Main.getPlayer().move(oldX, oldY + 1);
                    case 'd' -> Main.getPlayer().move(oldX + 1, oldY);
                    default -> System.out.println("Invalid key");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}