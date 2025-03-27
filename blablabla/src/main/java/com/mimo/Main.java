package com.mimo;

import com.mimo.foundation.Renderer;
import com.mimo.foundation.entitys.player.Player;

import lombok.Getter;

public class Main {
    @Getter
    public static Player player = new Player();
    public static boolean isRunning = true;
    public static void main(String[] args) {
        Thread controlThread = new Thread(() -> ControlHandler.handleKey());
        Thread renderThread = new Thread(() -> {
            try {
                Renderer.render();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        
        controlThread.start();
        renderThread.start();
        
        try {
            controlThread.join();
            renderThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
   }
}