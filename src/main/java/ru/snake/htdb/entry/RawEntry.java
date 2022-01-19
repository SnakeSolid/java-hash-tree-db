package ru.snake.htdb.entry;

import java.util.Arrays;

public class RawEntry {

	private final byte[] key;

	private final byte[] value;

	public RawEntry(byte[] key, byte[] value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * @return the key
	 */
	public byte[] getKey() {
		return key;
	}

	/**
	 * @return the value
	 */
	public byte[] getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "RawEntry [key=" + Arrays.toString(key) + ", value=" + Arrays.toString(value) + "]";
	}

}
