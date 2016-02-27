package com.example.examplemod;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

/**
 * Created by yuusuke on 2016/02/21.
 */
public class AutoTouch {
    static int num = 60;
        public static void AutoTouch(World world, int x, int y, int z){
            //Minecraftの全体データの取得
            Minecraft minecraft = Minecraft.getMinecraft();

            for (int i =0;i<num;i=i+12){
                for (int j = 0;j<num;j=j+12){
                    world.setBlock(x+i,y,z+j, Blocks.torch);
                    //minecraft.thePlayer.addChatComponentMessage(new ChatComponentText("松明を起きました"));
                }
            }

            for (int i =0;i<num;i=i+12){
                for (int j = 0;j<num;j=j+12){
                    world.setBlock(x-i,y,z+j, Blocks.torch);
                }
            }

            for (int i =0;i<num;i=i+12){
                for (int j = 0;j<num;j=j+12){
                    world.setBlock(x-i,y,z-j, Blocks.torch);
                }
            }

            for (int i =0;i<num;i=i+12){
                for (int j = 0;j<num;j=j+12){
                    world.setBlock(x+i,y,z-j, Blocks.torch);
                }
            }




        }
    public int underBlockCheck(int num){


        return num;
    }
}
