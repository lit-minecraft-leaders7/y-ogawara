package com.example.examplemod;

import com.oracle.tools.packager.Log;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

/**
 * Created by yuusuke on 2016/02/21.
 */
public class MyKeyInputHandler {
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        //Minecraftの全体データを取得
        Minecraft minecraft = Minecraft.getMinecraft();
        //ワールドデータを取得
        World world = minecraft.theWorld;

        //プレイヤーの位置情報を取得
        int playerX = (int) minecraft.thePlayer.posX;
        int playerY = (int) minecraft.thePlayer.posY;
        int playerZ = (int) minecraft.thePlayer.posZ;

        //L_keyをおした時(ExampleModに記載済み)
        if (ExampleMod.LKey.isPressed()) {
            //メソッドの呼び出し
            AutoTouch.AutoTouch(world,playerX,playerY,playerZ);
            //Minecraftのコンソールに出力
            minecraft.thePlayer.addChatComponentMessage(new ChatComponentText("L_keyを押しました"));
            //javaのコンソールに出力
            System.out.println("L_key");
        }
    }

}
