package com.pengu.lostthaumaturgy.net;

import java.util.UUID;

import com.pengu.hammercore.net.packetAPI.iPacket;
import com.pengu.hammercore.net.packetAPI.iPacketListener;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketSyncEntity implements iPacket, iPacketListener<PacketSyncEntity, iPacket>
{
	private UUID uuid;
	private NBTTagCompound nbt;
	private int id;
	
	public PacketSyncEntity(Entity entity)
	{
		uuid = entity.getUniqueID();
		id = entity.getEntityId();
		nbt = entity.writeToNBT(new NBTTagCompound());
	}
	
	public PacketSyncEntity()
	{
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		nbt.setUniqueId("p1", uuid);
		nbt.setTag("p2", this.nbt);
		nbt.setInteger("p3", id);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		uuid = nbt.getUniqueId("p1");
		this.nbt = nbt.getCompoundTag("p2");
		id = nbt.getInteger("p3");
	}
	
	@Override
	public iPacket onArrived(PacketSyncEntity packet, MessageContext context)
	{
		if(context.side == Side.CLIENT)
			packet.client();
		return null;
	}
	
	@SideOnly(Side.CLIENT)
	public void client()
	{
		Entity e = Minecraft.getMinecraft().world.getEntityByID(id);
		if(e != null)
		{
			NBTTagList nbttaglist = new NBTTagList();
			for(double d0 : new double[] { e.posX, e.posY, e.posZ })
				nbttaglist.appendTag(new NBTTagDouble(d0));
			nbt.setTag("Pos", nbttaglist);
			
			e.readFromNBT(nbt);
		}
	}
}