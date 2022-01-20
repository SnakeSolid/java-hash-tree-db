package ru.snake.htdb.serialize;

import java.nio.ByteBuffer;

import ru.snake.htdb.encoder.Decoder;
import ru.snake.htdb.encoder.Encoder;
import ru.snake.htdb.encoder.SizeCalculator;

public abstract class AbstractSerializer<T> implements Serializer<T> {

	@Override
	public byte[] serialize(T value) {
		SizeCalculator calculator = new SizeCalculator();
		bufferSize(calculator, value);

		ByteBuffer buffer = ByteBuffer.allocate(calculator.size());
		Encoder encoder = new Encoder(buffer);
		doSerialize(encoder, value);

		return buffer.array();
	}

	@Override
	public T deserialize(byte[] array) {
		ByteBuffer buffer = ByteBuffer.wrap(array);
		Decoder decoder = new Decoder(buffer);

		return doDeserialize(decoder);
	}

	public abstract void bufferSize(SizeCalculator calculator, T value);

	public abstract void doSerialize(Encoder encoder, T value);

	public abstract T doDeserialize(Decoder decoder);

}
