package ru.snake.htdb.encoder;

import java.nio.ByteBuffer;

public class LengthEncoder {

	public static int size(String string) {
		return 4 + string.getBytes().length;
	}

	public static void encode(ByteBuffer buffer, String value) {
		byte[] bytes = value.getBytes();

		buffer.putInt(bytes.length);
		buffer.put(bytes);
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
	public static String decode(ByteBuffer buffer) {
		int length = buffer.getInt();
		byte[] data = new byte[length];
		buffer.get(data);

		return new String(data);
	}

}
