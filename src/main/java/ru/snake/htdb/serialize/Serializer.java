package ru.snake.htdb.serialize;

/**
 * Convert value from byte array to Java type and vice versa.
 *
 * @author snake
 *
 * @param <T>
 *            type
 */
public interface Serializer<T> {

	/**
	 * Serialize type to byte array.
	 *
	 * @param value
	 *            value to serialize
	 * @return byte array
	 */
	public byte[] serialize(T value);

	/**
	 * Deserialize type to byte array.
	 *
	 * @param array
	 *            byte array to deserialize
	 * @return deserialized value
	 */
	public T deserialize(byte[] array);

}
