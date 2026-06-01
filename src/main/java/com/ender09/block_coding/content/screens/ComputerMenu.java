package com.ender09.block_coding.content.screens;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

import static com.ender09.block_coding.register.ModMenus.COMPUTER_MENU;

public class ComputerMenu extends AbstractContainerMenu
{

    public ComputerMenu(int containerId, Inventory playerInventory) {
        super(COMPUTER_MENU.get(), containerId);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return false;
    }
}
