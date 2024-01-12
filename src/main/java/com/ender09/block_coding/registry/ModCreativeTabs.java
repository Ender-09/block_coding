package com.ender09.block_coding.registry;


import com.ender09.block_coding.BlockCoding;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BlockCoding.MOD_ID);

    //List of Creative mod Tabs added:
    public static final RegistryObject<CreativeModeTab> MAIN_TAB = TABS.register("block_coding_main_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Items.REDSTONE))
                    .title(Component.translatable("creativetab.block_coding_main_tab"))
                    .displayItems(((pParameters, pOutput) -> {

                        //List of Items in Tab
                        pOutput.accept(ModBlocks.COMPUTER.get());
                    }))
                    .build());

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }
}
