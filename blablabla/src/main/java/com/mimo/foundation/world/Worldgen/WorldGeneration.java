package com.mimo.foundation.world.Worldgen;

import java.util.Random;

import com.mimo.Main;
import com.mimo.foundation.world.Tiles.Tile;
import com.mimo.foundation.world.Tiles.TileTypes;
import com.mimo.foundation.world.World;

public class WorldGeneration {

    public World generateWorld(String seedStr) {
        long seed = parseSeed(seedStr);
        return generateWorld(seed);
    }
    
    public World generateWorld(long seed) {
        World world = new World();
        world.setTiles(new Tile[World.WORLD_WIDTH][World.WORLD_HEIGHT]);
        Random random = new Random(seed);
        
        for (int x = 0; x < World.WORLD_WIDTH; x++) {
            for (int y = 0; y < World.WORLD_HEIGHT; y++) {
                int index = random.nextInt(3);
                TileTypes type;
                type = switch (index) {
                    case 0 -> TileTypes.Grass;
                    case 1 -> TileTypes.Water;
                    default -> TileTypes.Tree;
                };
                Tile tile = new Tile();
                tile.setX(x);
                tile.setY(y);
                tile.setType(type);
                tile.setDefaultType(type); // set the original type too
                world.addTile(x, y, tile);
            }
        }
        // Place the player tile without losing the underlying default
        Tile playerTile = world.getTiles()[Main.player.getX()][Main.player.getY()];
        playerTile.setDefaultType(playerTile.getType()); // Store the original type
        playerTile.setType(TileTypes.Player);
        // You can set defaultType of the player tile as needed (optional)
        world.addTile(Main.player.getX(), Main.player.getY(), playerTile);
        return world;
    }

    public String seedGenerate() {
        Random random = new Random();
        StringBuilder seed = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            seed.append(random.nextInt(9999));
            seed.append(".");
        }
        seed.append(random.nextInt(9999));
        return seed.toString();
    }
    
    private long parseSeed(String seedStr) {
        String numericSeed = seedStr.replace(".", "");
        try {
            return Long.parseLong(numericSeed);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("Seed must be in the format 1111.1111.1111 containing only digits and dots.");
        }
    }
}