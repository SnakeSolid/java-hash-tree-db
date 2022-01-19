package ru.snake.htdb.serialize;

import java.nio.ByteBuffer;

public class ShortSerializer implements Serializer<Short> {

	@Override
	public byte[] serialize(Short value) {
		ByteBuffer buffer = ByteBuffer.allocate(Short.BYTES);
		buffer.putShort(value);

		return buffer.array();
	}

	@Override
	public Short deserialize(byte[] array) {
		ByteBuffer buffer = ByteBuffer.wrap(array);

		return buffer.getShort();
	}

}
