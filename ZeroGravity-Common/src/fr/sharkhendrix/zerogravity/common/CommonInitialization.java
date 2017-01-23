package fr.sharkhendrix.zerogravity.common;

import lombok.experimental.UtilityClass;

import com.esotericsoftware.kryo.Kryo;

import fr.sharkhendrix.zerogravity.common.map.Map;

@UtilityClass
public class CommonInitialization {

	public static void initialize(Kryo kryo) {
		kryo.register(byte[].class);
		kryo.register(Map.class);
	}
}
