package com.mimo.foundation;

import com.mimo.Main;
import com.mimo.foundation.world.World;

public class Renderer {
    public static void render(World world) throws InterruptedException {
        while (Main.isRunning) {
            // clear screen before drawing
            System.out.print("\033[H\033[2J");
            System.out.flush();
            world.printOut();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
