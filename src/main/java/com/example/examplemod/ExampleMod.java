package com.example.examplemod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod {

    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";
    public static final  Block RAINBOW = new BlockRainbow();
    public static final Block blocksound = new BlockSound();

    @EventHandler
    public void init(FMLInitializationEvent event) {
        GameRegistry.addRecipe(new ItemStack(Blocks.diamond_block),
                "AAA",
                "AAA",
                "AAA",
                'A', Blocks.dirt);
        GameRegistry.addRecipe(new ItemStack(Blocks.diamond_block),
                " A ",
                "CBC",
                "CBC",
                'A', new ItemStack(Items.skull,1,4),
                'B',Blocks.tnt,
                'C',Items.gunpowder);

///////////////////////課題2//////////////////////////
        GameRegistry.registerBlock(new MyBlock(),"myblock");

///////////////////////課題3////////////////////////
        customSword();

////////////////課題4/////////////////////////////
        customFood();
        ///////BlockRainbow////////
        GameRegistry.registerBlock(RAINBOW,"rainbow");
        GameRegistry.addShapelessRecipe(new ItemStack(RAINBOW),new ItemStack(Blocks.dirt));
///////SoundBlock///////////////////
        GameRegistry.registerBlock(blocksound,"BlockSound");


//////MyRecipe//////////////////
        MinecraftForge.EVENT_BUS.register(new BlockBreakEventHandler());
    }


    public void customSword(){
        Item sword = new MyItem();
        GameRegistry.registerItem(sword,"my_sword");

        GameRegistry.addRecipe(new ItemStack(sword,1,50),
                " B ",
                "BAB",
                " B ",
                'A', new ItemStack(Items.diamond_sword),
                'b', Items.gunpowder);

    }

    private void customFood() {
        PotionEffect[] onigiri = {
                new PotionEffect(Potion.regeneration.id,1200,1),
                new PotionEffect(Potion.damageBoost.id,1200,1),
                new PotionEffect(Potion.moveSpeed.id,1200,1),
                new PotionEffect(Potion.jump.id,1200,1)
        };

        GameRegistry.registerItem(new ItemModFood("onigiri",1,0.5f,false, onigiri).setAlwaysEdible(),"onigiri");
    }
}
