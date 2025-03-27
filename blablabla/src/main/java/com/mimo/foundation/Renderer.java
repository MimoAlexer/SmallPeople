package com.mimo.foundation;

import com.mimo.Main;
import com.mimo.foundation.world.World;
import com.mimo.foundation.world.Worldgen.WorldGeneration;

public class Renderer {
    public static World world;
    public static void render() throws InterruptedException {
        WorldGeneration worldGenerator = new WorldGeneration();
        world = worldGenerator.generateWorld(worldGenerator.seedGenerate());
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
