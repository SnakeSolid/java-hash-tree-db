package ru.snake.htdb.serialize;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ru.snake.htdb.encoder.Decoder;
import ru.snake.htdb.encoder.Encoder;
import ru.snake.htdb.encoder.SizeCalculator;

class ClassSerializerTest {

	private static final Class VALUE = new Class();

	@Test
	public void serializeMustSerializeValue() {
		Serializer<Class> serializer = new TestSerializer();
		byte[] result = serializer.serialize(VALUE);

		Assertions.assertArrayEquals(
			new byte[] { 0, 0, 0, 1, 0, 0, 0, 6, 'l', 'e', 'n', 'g', 't', 'h', 'z', 'e', 'r', 'o', 0, 0 },
			result
		);
	}

	@Test
	public void deserializeMustDeserializeValue() {
		Serializer<Class> serializer = new TestSerializer();
		Class result = serializer
			.deserialize(new byte[] { 0, 0, 0, 2, 0, 0, 0, 4, 't', 'e', 's', 't', 's', 't', 'r', 'i', 'n', 'g', 0, 0 });

		Assertions.assertEquals(2, result.number);
		Assertions.assertEquals("test", result.stringLength);
		Assertions.assertEquals("string", result.stringZero);
	}

	private static class TestSerializer extends AbstractSerializer<Class> {
		@Override
		public void bufferSize(SizeCalculator calculator, Class value) {
			calculator.with(value.number)
				.withLength(value.stringLength)
				.withZero(value.stringZero)
				.withNullable(value.nullable, (s, v) -> s.withLength(v));
		}

		@Override
		public void doSerialize(Encoder encoder, Class value) {
			encoder.encode(value.number)
				.encodeLength(value.stringLength)
				.encodeZero(value.stringZero)
				.encodeNullable(value.nullable, (e, v) -> e.encodeLength(value.nullable));
		}

		@Override
		public Class doDeserialize(Decoder decoder) {
			Class value = new Class();

			decoder.decodeInt(v -> value.number = v)
				.decodeLength(v -> value.stringLength = v)
				.decodeZero(v -> value.stringZero = v)
				.decodeNullable(value.nullable, (d) -> d.decodeLength(v -> value.nullable = v));

			return value;
		}
	}

	private static class Class {
		private int number = 1;
		private String stringZero = "zero";
		private String stringLength = "length";
		private String nullable = null;
	}

}