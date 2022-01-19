package ru.snake.htdb.encoder;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.nio.ByteBuffer;

import org.junit.jupiter.api.Test;

public class OptionalEncoderTest {

	@Test
	public void sizeMustReturnOptional() {
		int result = OptionalEncoder.size(4);

		assertEquals(5, result);
	}

	@Test
	public void encodeMustSerializeNull() {
		ByteBuffer buffer = ByteBuffer.allocate(1);
		OptionalEncoder.encode(buffer, (Integer) null, (b, v) -> b.putInt(v));

		assertArrayEquals(new byte[] { 0 }, buffer.array());
	}

	@Test
	public void encodeMustSerializeValue() {
		ByteBuffer buffer = ByteBuffer.allocate(5);
		OptionalEncoder.encode(buffer, 4, (b, v) -> b.putInt(v));

		assertArrayEquals(new byte[] { 1, 0, 0, 0, 4 }, buffer.array());
	}

	@Test
	public void decodeMustDeserializeNull() {
		ByteBuffer buffer = ByteBuffer.wrap(new byte[] { 0 });
		Integer result = OptionalEncoder.decode(buffer, (b) -> b.getInt());

		assertNull(result);
	}

	@Test
	public void decodeMustDeserializeValue() {
		ByteBuffer buffer = ByteBuffer.wrap(new byte[] { 1, 0, 0, 0, 4 });
		Integer result = OptionalEncoder.decode(buffer, (b) -> b.getInt());

		assertEquals(4, result);
	}

}
