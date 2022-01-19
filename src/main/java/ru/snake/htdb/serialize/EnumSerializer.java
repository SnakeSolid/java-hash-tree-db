package ru.snake.htdb.serialize;

import java.nio.ByteBuffer;

public class EnumSerializer<E extends Enum<E>> implements Serializer<E> {

	private final E[] values;

	public EnumSerializer(final E[] values) {
		this.values = values;
	}

	@Override
	public byte[] serialize(E value) {
		ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
		buffer.putInt(value.ordinal());

		return buffer.array();
	}

	@Override
	public E deserialize(byte[] array) {
		ByteBuffer buffer = ByteBuffer.wrap(array);

		return values[buffer.getInt()];
	}

}
