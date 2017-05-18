package com.pengu.lostthaumaturgy.tile;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.mrdimka.hammercore.tile.TileSyncable;
import com.pengu.hammercore.net.utils.NetPropertyNumber;
import com.pengu.lostthaumaturgy.LostThaumaturgy;

public class TileCrystalOre extends TileSyncable
{
	public final NetPropertyNumber<Short> orientation = new NetPropertyNumber<Short>(this, (short) 1);
	public final NetPropertyNumber<Short> crystals = new NetPropertyNumber<Short>(this, (short) 2);
	
	@Override
	public void writeNBT(NBTTagCompound nbt)
	{
	}
	
	@Override
	public void readNBT(NBTTagCompound nbt)
	{
		if(nbt.hasKey("Orientation"))
		{
			orientation.set(nbt.getShort("Orientation"));
			LostThaumaturgy.LOG.warn("TileEntity " + this + " tried to load old NBT Key: \"Orientation\"=" + orientation.get() + ". It is going to be refactored to properties!");
		}
		
		if(nbt.hasKey("Crystals"))
		{
			crystals.set(nbt.getShort("Crystals"));
			LostThaumaturgy.LOG.warn("TileEntity " + this + " tried to load old NBT Key: \"Crystals\"=" + crystals.get() + ". It is going to be refactored to properties!");
		}
	}
	
	public static int suggestOrientationForWorldGen(World world, BlockPos pos)
	{
		List<EnumFacing> fs = new ArrayList<>();
		for(EnumFacing facing : EnumFacing.VALUES)
		{
			BlockPos p = pos.offset(facing);
			IBlockState state = world.getBlockState(p);
			if(!state.equals(Blocks.STONE.getStateFromMeta(0))) continue;
			fs.add(facing);
		}
		
		return fs.size() > 0 ? fs.get(world.rand.nextInt(fs.size())).getOpposite().ordinal() : -1;
	}
}