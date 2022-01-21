package ru.snake.htdb.encoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.ByteBuffer;

import org.junit.jupiter.api.Test;

public class DecoderTest {

	@Test
	public void decodeMustReturnByteSize() {
		ByteBuffer buffer = ByteBuffer.wrap(new byte[] { 1 });
		byte[] result = new byte[] { 0 };
		new Decoder(buffer).decodeByte(v -> result[0] = v);

		assertEquals(1, result[0]);
	}

	@Test
	public void decodeMustReturnCharSize() {
		ByteBuffer buffer = ByteBuffer.wrap(new byte[] { 0, 1 });
		char[] result = new char[] { 0 };
		new Decoder(buffer).decodeChar(v -> result[0] = v);

		assertEquals(1, result[0]);
	}

	@Test
	public void decodeMustReturnShortSize() {
		ByteBuffer buffer = ByteBuffer.wrap(new byte[] { 0, 1 });
		short[] result = new short[] { 0 };
		new Decoder(buffer).decodeShort(v -> result[0] = v);

		assertEquals(1, result[0]);
	}

	@Test
	public void decodeMustReturnIntSize() {
		ByteBuffer buffer = ByteBuffer.wrap(new byte[] { 0, 0, 0, 1 });
		int[] result = new int[] { 0 };
		new Decoder(buffer).decodeInt(v -> result[0] = v);

		assertEquals(1, result[0]);
	}

	@Test
	public void decodeMustReturnLongSize() {
		ByteBuffer buffer = ByteBuffer.wrap(new byte[] { 0, 0, 0, 0, 0, 0, 0, 1 });
		long[] result = new long[] { 0 };
		new Decoder(buffer).decodeLong(v -> result[0] = v);

		assertEquals(1, result[0]);
	}

	@Test
	public void decodeMustReturnStringLengthSize() {
		ByteBuffer buffer = ByteBuffer.wrap(new byte[] { 0, 0, 0, 4, 't', 'e', 's', 't' });
		String[] result = new String[] { null };
		new Decoder(buffer).decodeLength(v -> result[0] = v);

		assertEquals("test", result[0]);
	}

	@Test
	public void decodeMustReturnStringZeroSize() {
		ByteBuffer buffer = ByteBuffer.wrap(new byte[] { 't', 'e', 's', 't', 0 });
		String[] result = new String[] { null };
		new Decoder(buffer).decodeZero(v -> result[0] = v);

		assertEquals("test", result[0]);
	}

	@Test
	public void decodeMustReturnValueSize() {
		ByteBuffer buffer = ByteBuffer.wrap(new byte[] { 1, 't', 'e', 's', 't', 0 });
		String[] result = new String[] { null };
		new Decoder(buffer).decodeNullable(d -> d.decodeZero(v -> result[0] = v));

		assertEquals("test", result[0]);
	}

	@Test
	public void decodeMustReturnNullSize() {
		ByteBuffer buffer = ByteBuffer.wrap(new byte[] { 0 });
		String[] result = new String[] { null };
		new Decoder(buffer).decodeNullable(d -> d.decodeZero(v -> result[0] = v));

		assertEquals(null, result[0]);
	}

}
