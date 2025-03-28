package com.mimo.foundation.world;

import com.mimo.Main;
import com.mimo.foundation.world.Tiles.Tile;
import com.mimo.foundation.world.Tiles.TileTypes;

import lombok.Getter;
import lombok.Setter;

public class World {
    @Getter @Setter
    private Tile[][] tiles;
    public static int WORLD_WIDTH = 10;
    public static int WORLD_HEIGHT = 10;

    public void addTile(int x, int y, Tile tile) {
        this.tiles[x][y] = tile;
    }

    public void printOut() {
        for (int y = 0; y < WORLD_HEIGHT; y++) {
            for (int x = 0; x < WORLD_WIDTH; x++) {
                Tile tile = tiles[x][y];
                String symbol;
                java.awt.Color color;
                if (x == Main.getPlayer().getX() && y == Main.getPlayer().getY()) {
                    symbol = com.mimo.foundation.world.Tiles.TileTypes.Player.getSymbol();
                    color = com.mimo.foundation.world.Tiles.TileTypes.Player.getColor();
                } else {
                    symbol = tile.getDefaultType().getSymbol();
                    color = tile.getDefaultType().getColor();
                }
                System.out.printf("\033[38;2;%d;%d;%dm%s \033[0m", 
                    color.getRed(), color.getGreen(), color.getBlue(), symbol);
            }
            System.out.println();
        }
    }
}
