package com.pengu.lostthaumaturgy.net;

import com.pengu.hammercore.net.packetAPI.iPacket;
import com.pengu.hammercore.net.packetAPI.iPacketListener;
import com.pengu.lostthaumaturgy.LostThaumaturgy;
import com.pengu.lostthaumaturgy.custom.aura.AtmosphereChunk;
import com.pengu.lostthaumaturgy.custom.aura.AtmosphereTicker;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketUpdateClientAura implements iPacket, iPacketListener<PacketUpdateClientAura, iPacket>
{
	public static BlockPos closestMonolith;
	
	public AtmosphereChunk chunk;
	public BlockPos monolith;
	
	public PacketUpdateClientAura(AtmosphereChunk chunk, EntityPlayerMP mp)
	{
		this.chunk = chunk;
		monolith = AtmosphereTicker.getClosestMonolithPos(mp.getPosition());
	}
	
	public PacketUpdateClientAura()
	{
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		if(chunk != null)
			nbt.setTag("Data", chunk.serializeNBT());
		if(monolith != null)
			nbt.setLong("p", monolith.toLong());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		chunk = new AtmosphereChunk();
		chunk.deserializeNBT(nbt.getCompoundTag("Data"));
		if(nbt.hasKey("p", NBT.TAG_LONG))
			monolith = BlockPos.fromLong(nbt.getLong("p"));
	}
	
	@Override
	public iPacket onArrived(PacketUpdateClientAura packet, MessageContext context)
	{
		LostThaumaturgy.proxy.updateClientAuraChunk(packet.chunk);
		closestMonolith = packet.monolith;
		return null;
	}
}