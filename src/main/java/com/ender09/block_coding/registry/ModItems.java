package com.ender09.block_coding.registry;

import com.ender09.block_coding.BlockCoding;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BlockCoding.MOD_ID);

    //List of registered Items:


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
