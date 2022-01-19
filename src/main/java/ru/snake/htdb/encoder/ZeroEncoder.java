package ru.snake.htdb.encoder;

import java.nio.ByteBuffer;

public class ZeroEncoder {

	public static int size(String string) {
		if (string == null) {
			return 0;
		}

		return 1 + string.getBytes().length;
	}

	public static void encode(ByteBuffer buffer, String value) {
		byte[] bytes = value.getBytes();

		buffer.put(bytes);
		buffer.put((byte) 0);
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
	public static String decode(ByteBuffer buffer) {
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
