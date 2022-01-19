package ru.snake.htdb.serialize;

public class StringSerializer implements Serializer<String> {

	@Override
	public byte[] serialize(String value) {
		return value.getBytes();
	}

	@Override
	public String deserialize(byte[] array) {
		return new String(array);
	}

}
