package me.drmarky.armordyer.Utilities;

import me.drmarky.armordyer.Main;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.HashMap;

public class Utils {

    public static ItemStack setName(ItemStack itemStack, String name) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static void setConcrete(Inventory gui, int i, int b, String name) {
        gui.setItem(i, setName(new ItemStack(Material.CONCRETE, 1, (byte) b), name));
    }

    public static void setArmor(Inventory gui, int i, Material material) {
        gui.setItem(i, new ItemStack(material));
    }


    public static void arrangeGUI(Inventory gui) {

        setConcrete(gui, 1, 15, "Black");
        setConcrete(gui, 10, 7, "Dark Grey");
        setConcrete(gui, 19, 8, "Light Grey");
        setConcrete(gui, 28, 0, "White");

        setConcrete(gui, 2, 12, "Brown");
        setConcrete(gui, 11, 14, "Red");
        setConcrete(gui, 20, 1, "Orange");
        setConcrete(gui, 29, 4, "Yellow");

        setArmor(gui, 4, Material.LEATHER_HELMET);
        setArmor(gui, 13, Material.LEATHER_CHESTPLATE);
        setArmor(gui, 22, Material.LEATHER_LEGGINGS);
        setArmor(gui, 31, Material.LEATHER_BOOTS);
        gui.setItem(40, setName(new ItemStack(Material.ARROW), "Undo"));

        setConcrete(gui, 6, 5, "Light Green");
        setConcrete(gui, 15, 13, "Green");
        setConcrete(gui, 24, 9, "Cyan");
        setConcrete(gui, 33, 3, "Light Blue");

        setConcrete(gui, 7, 6, "Pink");
        setConcrete(gui, 16, 2, "Magenta");
        setConcrete(gui, 25, 10, "Purple");
        setConcrete(gui, 34, 11, "Blue");

    }

    public static HashMap<String, DyeColor> colorMap = new HashMap<String, DyeColor>() {{
        put("Black", DyeColor.BLACK);
        put("Dark Grey", DyeColor.GRAY);
        put("Light Grey", DyeColor.SILVER);
        put("White", DyeColor.WHITE);
        put("Brown", DyeColor.BROWN);
        put("Red", DyeColor.RED);
        put("Orange", DyeColor.ORANGE);
        put("Yellow", DyeColor.YELLOW);
        put("Light Green", DyeColor.LIME);
        put("Green", DyeColor.GREEN);
        put("Cyan", DyeColor.CYAN);
        put("Light Blue", DyeColor.LIGHT_BLUE);
        put("Pink", DyeColor.PINK);
        put("Magenta", DyeColor.MAGENTA);
        put("Purple", DyeColor.PURPLE);
        put("Blue", DyeColor.BLUE);
    }};

    public static void forceColorUpdate(Inventory gui, int r, int g, int b) {

        ArrayList<Integer> armorSlots = new ArrayList<>();
        armorSlots.add(4);
        armorSlots.add(13);
        armorSlots.add(22);
        armorSlots.add(31);

        for (Integer i : armorSlots) {
            ItemStack itemStack = gui.getItem(i);
            LeatherArmorMeta meta = (LeatherArmorMeta) itemStack.getItemMeta();
            meta.setColor(Color.fromRGB(r, g, b));
            itemStack.setItemMeta(meta);
        }
    }

    public static void mixUpdateColor(Inventory gui, DyeColor dyeColor) {

        ArrayList<Integer> armorSlots = new ArrayList<>();
        armorSlots.add(4);
        armorSlots.add(13);
        armorSlots.add(22);
        armorSlots.add(31);

        for (Integer i : armorSlots) {
            ItemStack itemStack = gui.getItem(i);
            LeatherArmorMeta meta = (LeatherArmorMeta) itemStack.getItemMeta();
            meta.setColor(meta.getColor().mixColors(dyeColor.getColor()));
            itemStack.setItemMeta(meta);
        }
    }

    public static void updatePrevious(Main main, Player player) {

        LeatherArmorMeta meta = (LeatherArmorMeta) main.directory.get(player).gui.getItem(4).getItemMeta();

        main.directory.get(player).previousR = meta.getColor().getRed();
        main.directory.get(player).previousG = meta.getColor().getGreen();
        main.directory.get(player).previousB = meta.getColor().getBlue();

    }

}
