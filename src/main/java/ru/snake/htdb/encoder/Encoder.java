package ru.snake.htdb.encoder;

import java.nio.ByteBuffer;
import java.util.function.BiFunction;

public class Encoder {

	private static final byte ZERO = (byte) 0;

	private static final byte ONE = (byte) 1;

	private final ByteBuffer buffer;

	public Encoder(ByteBuffer buffer) {
		this.buffer = buffer;
	}

	public Encoder encode(byte value) {
		buffer.put(value);

		return this;
	}

	public Encoder encode(char value) {
		buffer.putChar(value);

		return this;
	}

	public Encoder encode(short value) {
		buffer.putShort(value);

		return this;
	}

	public Encoder encode(int value) {
		buffer.putInt(value);

		return this;
	}

	public Encoder encode(long value) {
		buffer.putLong(value);

		return this;
	}

	public Encoder encode(float value) {
		buffer.putFloat(value);

		return this;
	}

	public Encoder encode(double value) {
		buffer.putDouble(value);

		return this;
	}

	public Encoder encodeLength(String value) {
		buffer.putInt(value.length());
		buffer.put(value.getBytes());

		return this;
	}

	public Encoder encodeZero(String value) {
		buffer.put(value.getBytes());
		buffer.put(ZERO);

		return this;
	}

	public <T> Encoder encodeNullable(T value, BiFunction<Encoder, T, Encoder> callback) {
		if (value == null) {
			buffer.put(ZERO);
		} else {
			buffer.put(ONE);

			callback.apply(this, value);
		}

		return this;
	}

	@Override
	public String toString() {
		return "Encoder [buffer=" + buffer + "]";
	}

}
