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

        // Generate each tile using a noise function
        for (int x = 0; x < World.WORLD_WIDTH; x++) {
            for (int y = 0; y < World.WORLD_HEIGHT; y++) {
                double n = noise(x, y, seed);
                TileTypes type;
                if (n < 0.3) {
                    type = TileTypes.Water;
                } else if (n < 0.6) {
                    type = TileTypes.Grass;
                } else {
                    type = TileTypes.Tree;
                }
                Tile tile = new Tile();
                tile.setX(x);
                tile.setY(y);
                tile.setType(type);
                tile.setDefaultType(type);
                world.addTile(x, y, tile);
            }
        }
        
        // Apply smoothing to create more contiguous areas
        smoothWorld(world, 3);
        
        // Place the player tile without losing the underlying default
        Tile playerTile = world.getTiles()[Main.player.getX()][Main.player.getY()];
        playerTile.setDefaultType(playerTile.getType());
        playerTile.setType(TileTypes.Player);
        world.addTile(Main.player.getX(), Main.player.getY(), playerTile);
        return world;
    }
    
    // A simple noise function that returns a value between 0 and 1 for a given coordinate and seed
    private double noise(int x, int y, long seed) {
        long n = x + y * 57 + seed * 131;
        n = (n << 13) ^ n;
        double noise = (1.0 - ((n * (n * n * 15731 + 789221) + 1376312589) & 0x7fffffff) / 1073741824.0);
        // Normalize from [-1, 1] to [0, 1]
        return (noise + 1) / 2.0;
    }
    
    // Smoothing algorithm: for each tile, set its type to the majority type of its neighbors
    private void smoothWorld(World world, int iterations) {
        Tile[][] tiles = world.getTiles();
        int width = World.WORLD_WIDTH;
        int height = World.WORLD_HEIGHT;
        for (int it = 0; it < iterations; it++) {
            Tile[][] newTiles = new Tile[width][height];
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int waterCount = 0, grassCount = 0, treeCount = 0;
                    // Count neighbors (including self for stability)
                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            int nx = x + dx;
                            int ny = y + dy;
                            if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                                TileTypes neighborType = tiles[nx][ny].getType();
                                if (neighborType == TileTypes.Water) waterCount++;
                                else if (neighborType == TileTypes.Grass) grassCount++;
                                else if (neighborType == TileTypes.Tree) treeCount++;
                            }
                        }
                    }
                    TileTypes majorityType;
                    if (waterCount > grassCount && waterCount > treeCount) {
                        majorityType = TileTypes.Water;
                    } else if (grassCount > waterCount && grassCount > treeCount) {
                        majorityType = TileTypes.Grass;
                    } else {
                        majorityType = TileTypes.Tree;
                    }
                    Tile newTile = new Tile();
                    newTile.setX(x);
                    newTile.setY(y);
                    newTile.setType(majorityType);
                    newTile.setDefaultType(majorityType);
                    newTiles[x][y] = newTile;
                }
            }
            tiles = newTiles;
        }
        world.setTiles(tiles);
    }
    
    public String generateSeed() {
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