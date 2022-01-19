package ru.snake.htdb.serialize;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LongSerializerTest {

	private static final long VALUE = 1;

	@Test
	public void serializeMustSerializeValue() {
		Serializer<Long> serializer = new LongSerializer();
		byte[] result = serializer.serialize(VALUE);

		Assertions.assertEquals(8, result.length);
		Assertions.assertEquals(0, result[0]);
		Assertions.assertEquals(0, result[1]);
		Assertions.assertEquals(0, result[2]);
		Assertions.assertEquals(0, result[3]);
		Assertions.assertEquals(0, result[4]);
		Assertions.assertEquals(0, result[5]);
		Assertions.assertEquals(0, result[6]);
		Assertions.assertEquals(1, result[7]);
	}

	@Test
	public void deserializeMustDeserializeValue() {
		Serializer<Long> serializer = new LongSerializer();
		Long result = serializer.deserialize(new byte[] { 0, 0, 0, 0, 0, 0, 0, 1 });

		Assertions.assertEquals(VALUE, result);
	}

}