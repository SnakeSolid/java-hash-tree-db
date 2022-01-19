package ru.snake.htdb.encoder;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.ByteBuffer;

import org.junit.jupiter.api.Test;

public class ZeroEncoderTest {

	@Test
	public void sizeMustReturnZero() {
		int result = ZeroEncoder.size("test");

		assertEquals(5, result);
	}

	@Test
	public void encodeMustReturnArray() {
		ByteBuffer buffer = ByteBuffer.allocate(5);
		ZeroEncoder.encode(buffer, "test");

		assertArrayEquals(new byte[] { 't', 'e', 's', 't', 0 }, buffer.array());
	}

	@Test
	public void decodeMustReturnString() {
		ByteBuffer buffer = ByteBuffer.wrap(new byte[] { 't', 'e', 's', 't', 0 });
		String result = ZeroEncoder.decode(buffer);

		assertEquals("test", result);
	}

}
