package ru.snake.htdb.encoder;

import java.nio.ByteBuffer;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class OptionalEncoder {

	private static final byte MISSING = (byte) 0;

	private static final byte PRESENT = (byte) 1;

	public static int size(int size) {
		return size + 1;
	}

	public static <T> void encode(ByteBuffer buffer, T value, BiConsumer<ByteBuffer, T> callback) {
		if (value == null) {
			buffer.put(MISSING);
		} else {
			buffer.put(PRESENT);
			callback.accept(buffer, value);
		}
	}

	public static <T> T decode(ByteBuffer buffer, Function<ByteBuffer, T> callback) {
		byte isPresent = buffer.get();

		if (isPresent == MISSING) {
			return null;
		} else {
			return callback.apply(buffer);
		}
	}

}
