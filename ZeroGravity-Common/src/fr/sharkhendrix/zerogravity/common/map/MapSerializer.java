package fr.sharkhendrix.zerogravity.common.map;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class MapSerializer extends Serializer<Map> {

	@Override
	public Map read(Kryo kryo, Input input, Class<Map> type) {
		int width = input.readInt();
		int height = input.readInt();
		byte[] data = kryo.readObject(input, byte[].class);
		Map map = new Map(width, height);
		map.set(data);
		return map;
	}

	@Override
	public void write(Kryo kryo, Output output, Map map) {
		output.writeInt(map.getWidth());
		output.writeInt(map.getHeight());
		kryo.writeObject(output, map.toByteArray());
	}

}
