package com.example.examplemod;

import com.sun.glass.ui.TouchInputSupport;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.settings.KeyBinding;
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
import org.lwjgl.input.Keyboard;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod {

    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";
    public static final  Block RAINBOW = new BlockRainbow();
    public static final  Block TouchTNT = new BlockTouchTNT();
    public static final Block blockSound = new BlockSound();
    public static final KeyBinding LKey = new KeyBinding("key.l", Keyboard.KEY_L, "leveling_switch");

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
        GameRegistry.registerBlock(blockSound,"BlockSound");


//////MyRecipe//////////////////
        MinecraftForge.EVENT_BUS.register(new BlockBreakEventHandler());

///////RedStone/////////////////
        GameRegistry.registerBlock(new BlockRedstoneInput(),"redstone_input");
        GameRegistry.registerBlock(new BlockRedstoneClock(),"redstone_clock");
//////Snowball/////////////////

        GameRegistry.registerItem(new ItemMySnowball(),"snowball");
//////footprintssand//////////////
        GameRegistry.registerBlock(new FootprintsSand(),"footprintssand");
        ClientRegistry.registerKeyBinding(LKey);

        FMLCommonHandler.instance().bus().register(new MyKeyInputHandler());

        TTNT();




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
//    public void customSnowBall(){
//        Item mySnowBall = new ItemMySnowball();
//        GameRegistry.registerItem(mySnowBall,"my_snow");
//
//        GameRegistry.addRecipe(new ItemStack(mySnowBall,1,50),
//                "AAA",
//                "AAA",
//                "AAA",
//                'A', new ItemStack(MySnowGet.my_snow));
//
//    }

    private void customFood() {
        PotionEffect[] onigiri = {
                new PotionEffect(Potion.regeneration.id,1200,1),
                new PotionEffect(Potion.damageBoost.id,1200,1),
                new PotionEffect(Potion.moveSpeed.id,1200,1),
                new PotionEffect(Potion.jump.id,1200,1)
        };

        GameRegistry.registerItem(new ItemModFood("onigiri",1,0.5f,false, onigiri).setAlwaysEdible(),"onigiri");
    }
    void TTNT (){
        GameRegistry.registerBlock(TouchTNT,"TouchTNT");
        GameRegistry.addRecipe(new ItemStack(TouchTNT),
                "BBB",
                "BAB",
                "BBB",
                'A', Blocks.tnt,'B',Blocks.torch);
        GameRegistry.addShapelessRecipe(new ItemStack(TouchTNT),new ItemStack(Blocks.dirt));

        MinecraftForge.EVENT_BUS.register(new PlayerDeathEventHandler());
    }
}
