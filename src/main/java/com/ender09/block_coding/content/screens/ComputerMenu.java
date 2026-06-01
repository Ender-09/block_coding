package com.ender09.block_coding.content.screens;

import com.ender09.block_coding.register.ModMenus;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class ComputerMenu extends AbstractContainerMenu
{

    public ComputerMenu(int containerId, Inventory playerInventory) {
        super(ModMenus.COMPUTER_MENU.get(), containerId);
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
