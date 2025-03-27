package com.mimo.foundation.entitys.player;

import com.mimo.foundation.world.World;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
public class Player {

    private int health = 100;
    private int x = World.WORLD_HEIGHT / 2;
    private int y = World.WORLD_WIDTH / 2;
     
    public Player() {
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
