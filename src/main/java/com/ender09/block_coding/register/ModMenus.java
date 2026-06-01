package com.ender09.block_coding.register;

import com.ender09.block_coding.BlockCoding;
import com.ender09.block_coding.content.screens.ComputerMenu;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModMenus
{
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(net.minecraft.core.registries.BuiltInRegistries.MENU, BlockCoding.MODID);

    //List of menus to register
    public static final Supplier<MenuType<ComputerMenu>> COMPUTER_MENU = MENUS.register("computer_menu", () -> new MenuType<>(ComputerMenu::new, FeatureFlags.DEFAULT_FLAGS));


    public static void register(IEventBus eventBus)
    {
        MENUS.register(eventBus);
    }
}
