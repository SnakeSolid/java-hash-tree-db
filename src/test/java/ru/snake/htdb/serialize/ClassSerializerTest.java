package ru.snake.htdb.serialize;

import java.nio.ByteBuffer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ru.snake.htdb.encoder.LengthEncoder;
import ru.snake.htdb.encoder.OptionalEncoder;
import ru.snake.htdb.encoder.ZeroEncoder;

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
		public int bufferSize(Class value) {
			return SIZE_INTEGER + LengthEncoder.size(value.stringLength) + ZeroEncoder.size(value.stringZero)
					+ OptionalEncoder.size(ZeroEncoder.size(value.nullable));
		}

		@Override
		public void doSerialize(Class value, ByteBuffer buffer) {
			buffer.putInt(value.number);
			LengthEncoder.encode(buffer, value.stringLength);
			ZeroEncoder.encode(buffer, value.stringZero);
			OptionalEncoder.encode(buffer, value.nullable, (b, v) -> ZeroEncoder.encode(buffer, v));
		}

		@Override
		public Class doDeserialize(ByteBuffer buffer) {
			Class value = new Class();
			value.number = buffer.getInt();
			value.stringLength = LengthEncoder.decode(buffer);
			value.stringZero = ZeroEncoder.decode(buffer);
			value.nullable = OptionalEncoder.decode(buffer, (b) -> ZeroEncoder.decode(buffer));

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