package com.ender09.block_coding.foundation.registry;

import com.ender09.block_coding.BlockCoding;
import com.ender09.block_coding.content.computer.ComputerBlock;
import com.ender09.block_coding.content.network_transceiver.NetworkTransceiverBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BlockCoding.MOD_ID);

    //List of registered Blocks:
    public static final RegistryObject<Block> COMPUTER = registerBlock("computer",
            () -> new ComputerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> NETWORK_TRANSCEIVER = registerBlock("network_transceiver",
            () -> new NetworkTransceiverBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    //Helper Functions
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name,toReturn);
        return  toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name,
                () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}