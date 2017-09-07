package com.pengu.lostthaumaturgy.api.match;

public interface iMatcher<T>
{
	T defaultInstance();
	
	boolean matches(T t);
}