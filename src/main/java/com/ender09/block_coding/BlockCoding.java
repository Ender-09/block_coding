package com.ender09.block_coding;

import com.ender09.block_coding.foundation.visual_scripting.nodes.DeviceNodeGenerator;
import com.ender09.block_coding.foundation.visual_scripting.nodes.NodeGenerator;
import com.ender09.block_coding.registry.*;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import org.slf4j.Logger;

import java.util.function.Supplier;

@Mod(BlockCoding.MOD_ID)
public class BlockCoding
{
    public static final String MOD_ID = "block_coding";
    private static final Logger LOGGER = LogUtils.getLogger();

    public BlockCoding()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);

        ModBlocks.register(modEventBus);
        ModBlockEntityTypes.register(modEventBus);

        NODE_GENERATOR.register(modEventBus);
        DEVICE_NODE_GENERATOR.register(modEventBus);
    }


    public static final ResourceLocation NODE_GENERATOR_REGISTRY_KEY = new ResourceLocation(BlockCoding.MOD_ID, "node_generator");
    public static final ResourceLocation DEVICE_NODE_GENERATOR_REGISTRY_KEY = new ResourceLocation(BlockCoding.MOD_ID, "device_node_generator");

    public static final DeferredRegister<NodeGenerator> NODE_GENERATOR = DeferredRegister.create(NODE_GENERATOR_REGISTRY_KEY,
            BlockCoding.MOD_ID);
    public static final Supplier<IForgeRegistry<NodeGenerator>> NODE_GENERATOR_REGISTRY = NODE_GENERATOR.makeRegistry(
            RegistryBuilder::new);

    public static final DeferredRegister<DeviceNodeGenerator> DEVICE_NODE_GENERATOR = DeferredRegister.create(DEVICE_NODE_GENERATOR_REGISTRY_KEY,
            BlockCoding.MOD_ID);
    public static final Supplier<IForgeRegistry<DeviceNodeGenerator>> DEVICE_NODE_GENERATOR_REGISTRY = DEVICE_NODE_GENERATOR.makeRegistry(
            RegistryBuilder::new);
}
