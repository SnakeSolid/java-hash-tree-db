package ru.snake.htdb.encoder;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.nio.ByteBuffer;

import org.junit.jupiter.api.Test;

public class EncoderTest {

	@Test
	public void encodeMustReturnByteSize() {
		ByteBuffer buffer = ByteBuffer.allocate(1);

		new Encoder(buffer).encode((byte) 1);

		assertArrayEquals(new byte[] { 1 }, buffer.array());
	}

	@Test
	public void encodeMustReturnCharSize() {
		ByteBuffer buffer = ByteBuffer.allocate(2);

		new Encoder(buffer).encode((char) 1);

		assertArrayEquals(new byte[] { 0, 1 }, buffer.array());
	}

	@Test
	public void encodeMustReturnShortSize() {
		ByteBuffer buffer = ByteBuffer.allocate(2);

		new Encoder(buffer).encode((short) 1);

		assertArrayEquals(new byte[] { 0, 1 }, buffer.array());
	}

	@Test
	public void encodeMustReturnIntSize() {
		ByteBuffer buffer = ByteBuffer.allocate(4);

		new Encoder(buffer).encode((int) 1);

		assertArrayEquals(new byte[] { 0, 0, 0, 1 }, buffer.array());
	}

	@Test
	public void encodeMustReturnLongSize() {
		ByteBuffer buffer = ByteBuffer.allocate(8);

		new Encoder(buffer).encode((long) 1);

		assertArrayEquals(new byte[] { 0, 0, 0, 0, 0, 0, 0, 1 }, buffer.array());
	}

	@Test
	public void encodeMustReturnStringLengthSize() {
		ByteBuffer buffer = ByteBuffer.allocate(8);

		new Encoder(buffer).encodeLength("test");

		assertArrayEquals(new byte[] { 0, 0, 0, 4, 't', 'e', 's', 't' }, buffer.array());
	}

	@Test
	public void encodeMustReturnStringZeroSize() {
		ByteBuffer buffer = ByteBuffer.allocate(5);

		new Encoder(buffer).encodeZero("test");

		assertArrayEquals(new byte[] { 't', 'e', 's', 't', 0 }, buffer.array());
	}

	@Test
	public void encodeMustReturnValueSize() {
		ByteBuffer buffer = ByteBuffer.allocate(6);

		new Encoder(buffer).encodeNullable("test", (e, v) -> e.encodeZero(v));

		assertArrayEquals(new byte[] { 1, 't', 'e', 's', 't', 0 }, buffer.array());
	}

	@Test
	public void encodeMustReturnNullSize() {
		ByteBuffer buffer = ByteBuffer.allocate(1);

		new Encoder(buffer).encodeNullable((String) null, (e, v) -> e.encodeZero(v));

		assertArrayEquals(new byte[] { 0 }, buffer.array());
	}

}
