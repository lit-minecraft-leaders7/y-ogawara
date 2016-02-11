package com.example.examplemod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import scala.tools.nsc.doc.model.Public;

/**
 * Created by yuusuke on 2016/02/11.
 */
public class BlockRedstoneInput extends Block {
    public IIcon[] icons = new IIcon[4];


    public BlockRedstoneInput(){
        super(Material.rock);
        setCreativeTab(CreativeTabs.tabRedstone);

        setBlockName(ExampleMod.MODID+"BlockRedstoneInput");
        setBlockTextureName(ExampleMod.MODID+":"+"redstone_input");

        setHardness(100F);
    }

    //ブロックのテクスチャを変更する
    @Override
    public void registerBlockIcons(IIconRegister register){
        for (int i =0;i<4;i++){
            this.icons[i] = register.registerIcon(textureName + "_"+i);

        }
    }

    //テクスチャの決定に関するメソッド
    @Override
    public IIcon getIcon (int side,int metadata){
        //メタデータを元にテクスチャの決定
        return icons[metadata];
    }

    //レッドストーンとブロックの接続ができるかどうかのメソッド
    @Override
    public boolean canConnectRedstone(IBlockAccess world,int x,int y,int z,int side){
        return true;
    }
    //ブロックの周辺に変化があった時に呼ばれるメソッド
    @Override
    public void onNeighborBlockChange(World world,int x,int y,int z,Block block){
        //置かれたブロックに入ってきているパワーの合計を取得
        int sum = world.getBlockPowerInput(x,y,z);

        if (sum !=0){
            System.out.println("Start Input");
            System.out.println("input"+sum);
        }else{
            System.out.println("End Input");
        }
        //入力されたパワーを元にメタデータを設定
        world.setBlockMetadataWithNotify(x,y,z,sum/4,2);
    }
}
