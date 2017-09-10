package com.pengu.lostthaumaturgy.custom.aura;

import net.minecraft.util.math.BlockPos;

public class UnwardResult
{
	private final String wardReturn;
	private final ThaumosphereChunk chunkIn;
	private final BlockPos unward;
	
	UnwardResult(BlockPos pos, ThaumosphereChunk c, String wardReturn)
	{
		this.unward = pos;
		this.chunkIn = c;
		this.wardReturn = wardReturn;
	}
	
	public boolean isUnwardSuccessful()
	{
		return wardReturn != null;
	}
	
	public String getWardOwner()
	{
		return wardReturn;
	}
	
	public boolean undo()
	{
		return chunkIn.ward(unward, wardReturn);
	}
}