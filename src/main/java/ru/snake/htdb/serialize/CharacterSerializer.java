package ru.snake.htdb.serialize;

import java.nio.ByteBuffer;

public class CharacterSerializer implements Serializer<Character> {

	@Override
	public byte[] serialize(Character value) {
		ByteBuffer buffer = ByteBuffer.allocate(Character.BYTES);
		buffer.putChar(value);

		return buffer.array();
	}

	@Override
	public Character deserialize(byte[] array) {
		ByteBuffer buffer = ByteBuffer.wrap(array);

		return buffer.getChar();
	}

}
