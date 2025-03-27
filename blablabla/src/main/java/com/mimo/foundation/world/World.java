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
                if (x == Main.getPlayer().getX() && y == Main.getPlayer().getY()) {
                    System.out.print(TileTypes.Player.getSymbol() + " ");
                } else {
                    System.out.print(tiles[x][y].getDefaultType().getSymbol() + " ");
                }
            }
            System.out.println();
        }
    }
}
