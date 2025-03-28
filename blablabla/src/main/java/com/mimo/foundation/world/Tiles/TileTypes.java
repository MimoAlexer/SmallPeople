package com.mimo.foundation.world.Tiles;

import java.awt.Color;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public enum TileTypes {
    Tree("T", new Color(0, 100, 0)),
    Grass("G", new Color(0, 255, 0)),
    Water("W", new Color(0, 0, 255)),
    Player("P", new Color(255, 0, 0));
    String symbol;
    Color color;
}
