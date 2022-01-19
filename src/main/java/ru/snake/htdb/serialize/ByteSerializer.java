package ru.snake.htdb.serialize;

public class ByteSerializer implements Serializer<Byte> {

	@Override
	public byte[] serialize(Byte value) {
		return new byte[] { value };
	}

	@Override
	public Byte deserialize(byte[] array) {
		return array[0];
	}

}
