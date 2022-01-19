package ru.snake.htdb.serialize;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EnumSerializerTest {

	@Test
	public void serializeMustSerializeValue() {
		Serializer<Enum> serializer = new EnumSerializer<>(Enum.values());
		byte[] result = serializer.serialize(Enum.B);

		Assertions.assertArrayEquals(new byte[] { 0, 0, 0, 1 }, result);
	}

	@Test
	public void deserializeMustDeserializeValue() {
		Serializer<Enum> serializer = new EnumSerializer<>(Enum.values());
		Enum result = serializer.deserialize(new byte[] { 0, 0, 0, 1 });

		Assertions.assertEquals(Enum.B, result);
	}

	private enum Enum {
		A, B, C,
	}

}