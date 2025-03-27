package com.mimo.foundation.world.Tiles;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public enum TileTypes {
    Tree("🌳"),
    Grass("🌿"),
    Water("💦"),
    Player("👤");
    String symbol;
}
