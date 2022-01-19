package ru.snake.htdb.serialize;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShortSerializerTest {

	private static final short VALUE = 1;

	@Test
	public void serializeMustSerializeValue() {
		Serializer<Short> serializer = new ShortSerializer();
		byte[] result = serializer.serialize(VALUE);

		Assertions.assertArrayEquals(new byte[] { 0, 1 }, result);
	}

	@Test
	public void deserializeMustDeserializeValue() {
		Serializer<Short> serializer = new ShortSerializer();
		Short result = serializer.deserialize(new byte[] { 0, 1 });

		Assertions.assertEquals(VALUE, result);
	}

}