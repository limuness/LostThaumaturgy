package com.pengu.lostthaumaturgy.core.utils;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import com.google.common.base.Objects;

import net.minecraft.util.math.Vec3i;

public class SerializableVector implements Externalizable
{
	private Vec3i parent;
	
	public SerializableVector()
	{
	}
	
	public SerializableVector(Vec3i vec)
	{
		if(vec == null)
			throw new NullPointerException();
		parent = vec;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return (obj instanceof SerializableVector && ((SerializableVector) obj).parent.equals(parent)) || (obj instanceof Vec3i && Objects.equal(obj, parent));
	}
	
	public int getX()
	{
		return parent.getX();
	}
	
	public int getY()
	{
		return parent.getY();
	}
	
	public int getZ()
	{
		return parent.getZ();
	}
	
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
	{
		parent = new Vec3i(in.readInt(), in.readInt(), in.readInt());
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException
	{
		out.writeInt(parent.getX());
		out.writeInt(parent.getY());
		out.writeInt(parent.getZ());
	}
}