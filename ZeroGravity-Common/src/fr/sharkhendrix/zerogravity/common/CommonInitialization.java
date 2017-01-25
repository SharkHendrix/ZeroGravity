package fr.sharkhendrix.zerogravity.common;

import com.esotericsoftware.kryo.Kryo;

import fr.sharkhendrix.zerogravity.common.map.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonInitialization {

    public static void initialize(Kryo kryo) {
        kryo.register(byte[].class);
        kryo.register(Map.class);
    }
}
