package com.pengu.lostthaumaturgy.core.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.pengu.hammercore.api.iTileBlock;
import com.pengu.hammercore.common.utils.WorldUtil;
import com.pengu.lostthaumaturgy.core.Info;
import com.pengu.lostthaumaturgy.core.block.def.BlockRendered;
import com.pengu.lostthaumaturgy.core.tile.TilePenguCobbleGen;

public class BlockPenguCobbleGen extends BlockRendered implements iTileBlock<TilePenguCobbleGen>, ITileEntityProvider
{
	public BlockPenguCobbleGen()
	{
		super(Material.ROCK);
		setUnlocalizedName("pengu_cobble_gen");
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TilePenguCobbleGen();
	}
	
	@Override
	public Class<TilePenguCobbleGen> getTileClass()
	{
		return TilePenguCobbleGen.class;
	}
	
	@Override
	public String getParticleSprite(World world, BlockPos pos)
	{
		return Info.MOD_ID + ":blocks/pengu_cobble_gen";
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isFullBlock(IBlockState state)
	{
		return false;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		TilePenguCobbleGen tile = WorldUtil.cast(worldIn.getTileEntity(pos), TilePenguCobbleGen.class);
		if(tile != null)
			tile.inventory.drop(worldIn, pos);
		super.breakBlock(worldIn, pos, state);
	}
}