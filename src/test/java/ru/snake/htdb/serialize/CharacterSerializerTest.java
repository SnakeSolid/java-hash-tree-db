package ru.snake.htdb.serialize;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CharacterSerializerTest {

	private static final char VALUE = 1;

	@Test
	public void serializeMustSerializeValue() {
		Serializer<Character> serializer = new CharacterSerializer();
		byte[] result = serializer.serialize(VALUE);

		Assertions.assertArrayEquals(new byte[] { 0, 1 }, result);
	}

	@Test
	public void deserializeMustDeserializeValue() {
		Serializer<Character> serializer = new CharacterSerializer();
		Character result = serializer.deserialize(new byte[] { 0, 1 });

		Assertions.assertEquals(VALUE, result);
	}

}