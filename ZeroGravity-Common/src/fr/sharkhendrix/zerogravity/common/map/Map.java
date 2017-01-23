package fr.sharkhendrix.zerogravity.common.map;

import lombok.Getter;

import com.esotericsoftware.kryo.DefaultSerializer;

@DefaultSerializer(MapSerializer.class)
public class Map {

	public static final double TILE_WIDTH = 1;

	@Getter
	private int width;
	@Getter
	private int height;

	private Tile[] tiles;

	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new Tile[width * height];
	}

	public void set(int x, int y, Tile tile) {
		tiles[y * width + x] = tile;
	}

	public Tile get(int x, int y) {
		return tiles[y * width + x];
	}

	public void set(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			tiles[i] = Tile.get(data[i]);
		}
	}

	public byte[] toByteArray() {
		byte[] data = new byte[tiles.length];
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) tiles[i].getId();
		}
		return data;
	}
}
