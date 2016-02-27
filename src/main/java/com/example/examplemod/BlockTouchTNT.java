package com.example.examplemod;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;


import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;


import java.util.Random;

/**
 * Created by yuusuke on 2016/02/21.
 */
public class BlockTouchTNT extends Block {
    //deathEventHandlerで使う、flagの変数
    public static boolean myTNTFlag;

    //普通の配列
    public IIcon[] icons = new IIcon[7];
    //白いテクスチャの配列
    public IIcon[] whiteIcons = new IIcon[7];
    public BlockTouchTNT(){
        super(Material.tnt);

        setCreativeTab(CreativeTabs.tabBlock);

        setBlockName(ExampleMod.MODID+"BlockTouchTNT");
        setBlockTextureName(ExampleMod.MODID+":"+"ttnt");

        setHardness(100F);


    }
    @Override
    public void registerBlockIcons(IIconRegister register){

        //上の画像
        this.icons[0] = register.registerIcon(textureName + "_"+0);
        //下の画像
        this.icons[1] = register.registerIcon(textureName + "_"+1);
        //横は全部同じ画像
        for (int i =2;i<6;i++){
            this.icons[i] = register.registerIcon(textureName + "_"+2);
        }

        //白いテクスチャを設定
        for (int i = 0;i<6;i++){
            this.whiteIcons[i] = register.registerIcon(textureName + "_"+3);
        }

    }
    @Override
    public IIcon getIcon (int side,int metadata){
        if (metadata % 2== 0){
            //画像を普通に
            return icons[side];
        }else{
            //画像を白く
            return whiteIcons[side];
        }
    }


    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        int metadata = world.getBlockMetadata(x, y, z) + 1;
        world.setBlockMetadataWithNotify(x, y, z, metadata, 2);

        if (metadata == 13) {
             //TNTを松明に置き換えるメソッドの呼び出し
            AutoTouch.AutoTouch(world,x,y,z);
            TNTanimation(world,x,y,z);
        } else {
            //なんティックで呼び出すかが2の数字
            world.scheduleBlockUpdateWithPriority(x, y, z,this, 2, 100);
        }
    }



    //ブロックを右クリックした時に呼び出される
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float posX, float posY, float posZ) {
        //ここでプレイヤーがどのアイテムを持っているかを取得できる
        ItemStack heldItem = player.getHeldItem();
        //アイテムを持っていない場合
        if (heldItem == null) {
            return false;
        }
        //アイテムを持っているが、火打ち石ではない場合
        if (heldItem.getItem() != Items.flint_and_steel) {
            return false;
        }
        //flagを建てる
        myTNTFlag = true;

        world.scheduleBlockUpdateWithPriority(x,y,z,this,5,100);
        return true;
    }

//    //ブロックを左クリックした時に呼び出される
//    @Override
//    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player){
//    }
    void TNTanimation (World world,int x,int y,int z){
        //ここからTNT爆発のアニメーション
        Explosion explosion = new Explosion(world, null, x + 0.5f, y + 1.1f, z + 0.5f, 5.0f);
        //爆発した後に火を周りに出すか
        explosion.isFlaming = false;
        //Blockを壊すか壊さないか
        explosion.isSmoking = false;
        explosion.doExplosionA();
        explosion.doExplosionB(world.isRemote);
        if (!world.isRemote) {
            explosion.affectedBlockPositions.clear();
            for (Object playerEntity : world.playerEntities) {
                EntityPlayer entityplayer = (EntityPlayer) playerEntity;
                if (entityplayer.getDistanceSq(x, y, z) < 4096.0D) {
                    ((EntityPlayerMP) entityplayer).playerNetServerHandler.sendPacket(new S27PacketExplosion(x + 0.5f, y + 1.1f, z + 0.5f, 4.0f, explosion.affectedBlockPositions, (Vec3) explosion.func_77277_b().get(entityplayer)));
                }
            }
        }
        //終わり
    }
}