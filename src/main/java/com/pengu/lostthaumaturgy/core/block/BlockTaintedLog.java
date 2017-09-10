package com.pengu.lostthaumaturgy.core.block;

import java.util.Random;

import net.minecraft.block.BlockLog;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.pengu.lostthaumaturgy.api.blocks.iTaintedBlock;
import com.pengu.lostthaumaturgy.custom.aura.ThaumosphereChunk;
import com.pengu.lostthaumaturgy.custom.aura.ThaumosphereManager;

public class BlockTaintedLog extends BlockLog implements iTaintedBlock
{
	public BlockTaintedLog()
	{
		setUnlocalizedName("tainted_log");
		Blocks.FIRE.setFireInfo(this, 2, 2);
		setDefaultState(getDefaultState().withProperty(LOG_AXIS, EnumAxis.Y));
		setTickRandomly(true);
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, LOG_AXIS);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(LOG_AXIS, EnumAxis.values()[meta % EnumAxis.values().length]);
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(LOG_AXIS).ordinal();
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		if(!worldIn.isRemote)
		{
			ThaumosphereChunk ac = ThaumosphereManager.getAuraChunkFromBlockCoords(worldIn, pos);
			if(ac != null)
			{
				if(ac.goodVibes > 0)
					ac.goodVibes--;
				else
					ac.badVibes++;
			}
		}
	}
}