package com.mimo.foundation.world.Tiles;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Tile {
    private TileTypes type;
    private int x;
    private int y;
    // New field that stores the original state of the tile
    private TileTypes defaultType;
}