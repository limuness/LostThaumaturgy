package com.pengu.lostthaumaturgy.api.tiles;

import com.pengu.hammercore.api.handlers.iHandlerProvider;
import com.pengu.hammercore.utils.WorldLocation;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ConnectionManager
{
	public static iConnection getConnection(World world, BlockPos pos, EnumFacing facing)
	{
		TileEntity tile = world.getTileEntity(pos.offset(facing));
		if(tile != null)
		{
			iConnection c = null;
			if(tile.hasCapability(CapabilityVisConnection.VIS, facing.getOpposite()))
				c = tile.getCapability(CapabilityVisConnection.VIS, facing.getOpposite());
			else if(tile instanceof iConnection)
				c = (iConnection) tile;
			else if(tile instanceof iHandlerProvider)
			{
				iHandlerProvider prov = (iHandlerProvider) tile;
				if(prov.hasHandler(facing.getOpposite(), iConnection.class))
					return prov.getHandler(facing.getOpposite(), iConnection.class);
			}
			if(c != null && c.getConnectable(facing.getOpposite()))
				return c;
		}
		return null;
	}
	
	public static iConnection getConnection(WorldLocation loc, EnumFacing facing)
	{
		return getConnection(loc.getWorld(), loc.getPos(), facing);
	}
}