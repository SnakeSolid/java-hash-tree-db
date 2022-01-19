package ru.snake.htdb.serialize;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ByteSerializerTest {

	private static final byte VALUE = (byte) 1;

	@Test
	public void serializeMustSerializeValue() {
		Serializer<Byte> serializer = new ByteSerializer();
		byte[] result = serializer.serialize(VALUE);

		Assertions.assertArrayEquals(new byte[] { 1 }, result);
	}

	@Test
	public void deserializeMustDeserializeValue() {
		Serializer<Byte> serializer = new ByteSerializer();
		Byte result = serializer.deserialize(new byte[] { 1 });

		Assertions.assertEquals(VALUE, result);
	}

}