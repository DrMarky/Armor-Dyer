package me.drmarky.armordyer.Utilities;

import org.bukkit.inventory.Inventory;

public class PlayerObject {

    public Inventory gui;
    public int layerCount;
    public int previousR;
    public int previousG;
    public int previousB;

    public PlayerObject(Inventory gui, int layerCount, int previousR, int previousG, int previousB) {
        this.gui = gui;
        this.layerCount = layerCount;
        this.previousR = previousR;
        this.previousG = previousG;
        this.previousB = previousB;
    }
}
