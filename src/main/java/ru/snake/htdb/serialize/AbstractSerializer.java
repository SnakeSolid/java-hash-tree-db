package ru.snake.htdb.serialize;

import java.nio.ByteBuffer;

import ru.snake.htdb.encoder.SizeCalculator;

public abstract class AbstractSerializer<T> implements Serializer<T> {

	protected static int SIZE_BYTE = Byte.BYTES;

	protected static int SIZE_CHARACTER = Character.BYTES;

	protected static int SIZE_SHORT = Short.BYTES;

	protected static int SIZE_INTEGER = Integer.BYTES;

	protected static int SIZE_LONG = Long.BYTES;

	@Override
	public byte[] serialize(T value) {
		SizeCalculator calculator = new SizeCalculator();
		bufferSize(calculator, value);

		ByteBuffer buffer = ByteBuffer.allocate(calculator.size());
		doSerialize(buffer, value);

		return buffer.array();
	}

	@Override
	public T deserialize(byte[] array) {
		ByteBuffer buffer = ByteBuffer.wrap(array);

		return doDeserialize(buffer);
	}

	public abstract void bufferSize(SizeCalculator calculator, T value);

	public abstract void doSerialize(ByteBuffer buffer, T value);

	public abstract T doDeserialize(ByteBuffer buffer);

}
