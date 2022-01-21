package ru.snake.htdb.encoder;

import java.nio.ByteBuffer;
import java.util.function.Consumer;
import java.util.function.Function;

import ru.snake.htdb.encoder.setter.ByteSetter;
import ru.snake.htdb.encoder.setter.CharSetter;
import ru.snake.htdb.encoder.setter.DoubleSetter;
import ru.snake.htdb.encoder.setter.FloatSetter;
import ru.snake.htdb.encoder.setter.IntSetter;
import ru.snake.htdb.encoder.setter.LongSetter;
import ru.snake.htdb.encoder.setter.ShortSetter;
import ru.snake.htdb.exception.HTDBInvalidNullableException;

public class Decoder {

	private static final byte ZERO = (byte) 0;

	private static final byte ONE = (byte) 1;

	private final ByteBuffer buffer;

	public Decoder(ByteBuffer buffer) {
		this.buffer = buffer;
	}

	public Decoder decodeByte(ByteSetter setter) {
		setter.set(buffer.get());

		return this;
	}

	public Decoder decodeChar(CharSetter setter) {
		setter.set(buffer.getChar());

		return this;
	}

	public Decoder decodeShort(ShortSetter setter) {
		setter.set(buffer.getShort());

		return this;
	}

	public Decoder decodeInt(IntSetter setter) {
		setter.set(buffer.getInt());

		return this;
	}

	public Decoder decodeLong(LongSetter setter) {
		setter.set(buffer.getLong());

		return this;
	}

	public Decoder decodeFloat(FloatSetter setter) {
		setter.set(buffer.getFloat());

		return this;
	}

	public Decoder decodeDouble(DoubleSetter setter) {
		setter.set(buffer.getDouble());

		return this;
	}

	public Decoder decodeLength(Consumer<String> setter) {
		int length = buffer.getInt();
		byte[] data = new byte[length];
		buffer.get(data);

		setter.accept(new String(data));

		return this;
	}

	public Decoder decodeZero(Consumer<String> setter) {
		int position = buffer.position();

		while (buffer.get() != 0) {
		}

		int length = buffer.position() - position - 1;
		byte[] data = new byte[length];

		buffer.position(position);
		buffer.get(data);
		buffer.get();

		setter.accept(new String(data));

		return this;
	}

	public <T> Decoder decodeNullable(Function<Decoder, Decoder> callback) {
		switch (buffer.get()) {
		case ZERO:
			break;

		case ONE:
			callback.apply(this);
			break;

		default:
			throw new HTDBInvalidNullableException();
		}

		return this;
	}

	@Override
	public String toString() {
		return "Decoder [buffer=" + buffer + "]";
	}

}
