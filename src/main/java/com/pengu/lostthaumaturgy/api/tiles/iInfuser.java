package com.pengu.lostthaumaturgy.api.tiles;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;

public interface iInfuser
{
	@Nullable
	EntityPlayer getInitiator();
}