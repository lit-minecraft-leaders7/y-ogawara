package com.example.examplemod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by yuusuke on 2016/02/11.
 */
public class FootprintsSand extends Block{
    private IIcon[] icons = new IIcon[5];

    public FootprintsSand(){
        super(Material.sand);
        setBlockName(ExampleMod.MODID+"_"+"footprintssand");
        setBlockTextureName(ExampleMod.MODID+":"+"footprintssand");
        setCreativeTab(CreativeTabs.tabBlock);
        setHardness(1);
    }
    @Override
    public void registerBlockIcons(IIconRegister register){
        //5枚の画像をMinecraftに関連付け
        for (int i =0;i<5;i++){
            this.icons[i] = register.registerIcon(textureName + "_"+i);

        }

    }

    @Override
    public IIcon getIcon (int side,int metadata){
        return icons[metadata];
    }

    //動くものがブロックに衝突しているときに呼ばれるメソッド
    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity){
        //entityがプレイヤーである場合&&衝突したブロックのmetadataが4じゃない時
        if(entity instanceof EntityPlayer && world.getBlockMetadata(x,y,z) !=4){
            world.setBlockMetadataWithNotify(x,y,z,4,2);
            world.scheduleBlockUpdateWithPriority(x,y,z,this,5,100);
        }
    }
    //ブロックの衝突範囲を決めるときに呼び出される
    //たぶんonEntityCollidedWithBlockの後？
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world,int x,int y,int z){
        //yを0.9にすることによって少しだけ凹ませている
        return AxisAlignedBB.getBoundingBox(x,y,z,x+1,y+0.9,z+1);
    }

    @Override
    public void updateTick(World world,int x,int y,int z,Random random){
        int next = world.getBlockMetadata(x,y,z) -1;
        world.setBlockMetadataWithNotify(x,y,z,next,2);

        if (next !=0) {
            world.scheduleBlockUpdateWithPriority(x, y, z,this, 5, 100);
        }
    }

}
