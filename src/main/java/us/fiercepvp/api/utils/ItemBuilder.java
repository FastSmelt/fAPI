package us.fiercepvp.api.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class ItemBuilder {
    private final ItemStack item;

    private ItemBuilder(ItemStack item) {
        this.item = item;
    }

    public ItemBuilder(Material material) {
        this(new ItemStack(material));
    }

    public static ItemBuilder from(ItemStack item) {
        return new ItemBuilder(item);
    }

    public ItemBuilder amount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder durability(int durability) {
        item.setDurability((short) durability);
        return this;
    }

    public ItemBuilder name(String name) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder lore(String... lore) {
        ItemMeta meta = item.getItemMeta();
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder flags(ItemFlag... flags) {
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(flags);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder enchant(Enchantment enchantment, int level) {
        item.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder skullOwner(String playerName) {
        if (item.getType() != Material.SKULL_ITEM) {
            throw new IllegalStateException("Non-skull items can't have a skull owner");
        }

        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(playerName);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder color(Color color) {
        if (item.getType() == Material.LEATHER_BOOTS || item.getType() == Material.LEATHER_CHESTPLATE || item.getType() == Material.LEATHER_HELMET || item.getType() == Material.LEATHER_LEGGINGS) {
            LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
            meta.setColor(color);
            item.setItemMeta(meta);
            return this;
        } else {
            throw new IllegalArgumentException("color() only applicable for leather armor!");
        }
    }

    public ItemStack build() {
        return item;
    }
}
