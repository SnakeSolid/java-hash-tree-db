package ru.snake.htdb.serialize;

import java.nio.ByteBuffer;

public abstract class AbstractSerializer<T> implements Serializer<T> {

	protected static int SIZE_BYTE = Byte.BYTES;

	protected static int SIZE_CHARACTER = Character.BYTES;

	protected static int SIZE_SHORT = Short.BYTES;

	protected static int SIZE_INTEGER = Integer.BYTES;

	protected static int SIZE_LONG = Long.BYTES;

	@Override
	public byte[] serialize(T value) {
		ByteBuffer buffer = ByteBuffer.allocate(bufferSize(value));
		doSerialize(value, buffer);

		return buffer.array();
	}

	@Override
	public T deserialize(byte[] array) {
		ByteBuffer buffer = ByteBuffer.wrap(array);

		return doDeserialize(buffer);
	}

	public abstract int bufferSize(T value);

	public abstract void doSerialize(T value, ByteBuffer buffer);

	public abstract T doDeserialize(ByteBuffer buffer);

	protected void serializeUsingLength(ByteBuffer buffer, String value) {
		byte[] bytes = value.getBytes();

		buffer.putInt(bytes.length);
		buffer.put(bytes);
	}

	protected void serializeUsingZero(ByteBuffer buffer, String value) {
		byte[] bytes = value.getBytes();

		buffer.put(bytes);
		buffer.put((byte) 0);
	}

	/**
	 * Deserialize string with leading length. This method will scan buffer
	 * once. Length of string is saved before string. Length based encoding will
	 * break string sorting order for string.
	 *
	 * @param buffer
	 *            byte buffer
	 * @return deserialized string
	 */
	protected String deserializeUsingLength(ByteBuffer buffer) {
		int length = buffer.getInt();
		byte[] data = new byte[length];
		buffer.get(data);

		return new String(data);
	}

	/**
	 * Deserialize string with trailing zero encoding. This method will scan
	 * buffer twice. First pass finds leading zero. Second pass create string.
	 * Zero based encoding can be used to keep sorting order for string.
	 *
	 * @param buffer
	 *            byte buffer
	 * @return deserialized string
	 */
	protected String deserializeUsingZero(ByteBuffer buffer) {
		int position = buffer.position();

		while (buffer.get() != 0) {
		}

		int length = buffer.position() - position - 1;
		byte[] data = new byte[length];

		buffer.position(position);
		buffer.get(data);
		buffer.get();

		return new String(data);
	}

}
