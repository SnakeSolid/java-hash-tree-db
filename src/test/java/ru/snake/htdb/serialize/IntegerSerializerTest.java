package ru.snake.htdb.serialize;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntegerSerializerTest {

	private static final int VALUE = 1;

	@Test
	public void serializeMustSerializeValue() {
		Serializer<Integer> serializer = new IntegerSerializer();
		byte[] result = serializer.serialize(VALUE);

		Assertions.assertArrayEquals(new byte[] { 0, 0, 0, 1 }, result);
	}

	@Test
	public void deserializeMustDeserializeValue() {
		Serializer<Integer> serializer = new IntegerSerializer();
		Integer result = serializer.deserialize(new byte[] { 0, 0, 0, 1 });

		Assertions.assertEquals(VALUE, result);
	}

}