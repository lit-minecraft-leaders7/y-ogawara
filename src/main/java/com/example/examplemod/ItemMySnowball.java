package com.example.examplemod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by yuusuke on 2016/02/11.
 */
public class ItemMySnowball extends ItemSnowball{
    public ItemMySnowball(){
        super();
        setUnlocalizedName(ExampleMod.MODID +"_snow_ball");

        setTextureName("snowball");
    }

    public ItemMySnowball(Item my_snow) {
    }

    //右クリックした時に実行されるメソッド
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player){
        if (!player.capabilities.isCreativeMode){
            --itemStack.stackSize;
        }
        //雪球を投げた時の音を設定
        world.playSoundAtEntity(player,"random.bow",0.5F,0.4F/(itemRand.nextFloat() *0.4F+0.4F));

        if (!world.isRemote){
            world.spawnEntityInWorld(new EntityMySnowball(world,player));
        }
        return itemStack;
    }
}
