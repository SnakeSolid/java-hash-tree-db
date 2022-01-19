package ru.snake.htdb.serialize;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringSerializerTest {

	private static final String VALUE = "test";

	@Test
	public void serializeMustSerializeValue() {
		Serializer<String> serializer = new StringSerializer();
		byte[] result = serializer.serialize(VALUE);

		Assertions.assertArrayEquals(new byte[] { 't', 'e', 's', 't' }, result);
	}

	@Test
	public void deserializeMustDeserializeValue() {
		Serializer<String> serializer = new StringSerializer();
		String result = serializer.deserialize(new byte[] { 't', 'e', 's', 't' });

		Assertions.assertEquals(VALUE, result);
	}

}