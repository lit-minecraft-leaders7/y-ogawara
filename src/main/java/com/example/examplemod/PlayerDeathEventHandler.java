package com.example.examplemod;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

/**
 * Created by yuusuke on 2016/02/27.
 */
public class PlayerDeathEventHandler {
        @SubscribeEvent
        public void onPlayerHurt(LivingHurtEvent event) {
            if (event.entityLiving.worldObj.isRemote) {
                return;
            }
            if (event.entityLiving instanceof EntityPlayer) {
                //flagがtrueの時に体力減少を無効化する
                if (BlockTouchTNT.myTNTFlag ==true) {
                    event.setCanceled(true);
                    BlockTouchTNT.myTNTFlag = false;
                }
            }
        }
    }

