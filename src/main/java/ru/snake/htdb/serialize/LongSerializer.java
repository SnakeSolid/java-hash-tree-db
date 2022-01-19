package ru.snake.htdb.serialize;

import java.nio.ByteBuffer;

public class LongSerializer implements Serializer<Long> {

	@Override
	public byte[] serialize(Long value) {
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
		buffer.putLong(value);

		return buffer.array();
	}

	@Override
	public Long deserialize(byte[] array) {
		ByteBuffer buffer = ByteBuffer.wrap(array);

		return buffer.getLong();
	}

}
