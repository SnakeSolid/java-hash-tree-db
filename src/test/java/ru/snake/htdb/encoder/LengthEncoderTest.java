package ru.snake.htdb.encoder;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.ByteBuffer;

import org.junit.jupiter.api.Test;

public class LengthEncoderTest {

	@Test
	public void sizeMustReturnLength() {
		int result = LengthEncoder.size("test");

		assertEquals(8, result);
	}

	@Test
	public void encodeMustReturnArray() {
		ByteBuffer buffer = ByteBuffer.allocate(8);
		LengthEncoder.encode(buffer, "test");

		assertArrayEquals(new byte[] { 0, 0, 0, 4, 't', 'e', 's', 't' }, buffer.array());
	}

	@Test
	public void decodeMustReturnString() {
		ByteBuffer buffer = ByteBuffer.wrap(new byte[] { 0, 0, 0, 4, 't', 'e', 's', 't' });
		String result = LengthEncoder.decode(buffer);

		assertEquals("test", result);
	}

}
