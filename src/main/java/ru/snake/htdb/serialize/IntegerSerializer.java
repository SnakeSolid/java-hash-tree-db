package ru.snake.htdb.serialize;

import java.nio.ByteBuffer;

public class IntegerSerializer implements Serializer<Integer> {

	@Override
	public byte[] serialize(Integer value) {
		ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
		buffer.putInt(value);

		return buffer.array();
	}

	@Override
	public Integer deserialize(byte[] array) {
		ByteBuffer buffer = ByteBuffer.wrap(array);

		return buffer.getInt();
	}

}
