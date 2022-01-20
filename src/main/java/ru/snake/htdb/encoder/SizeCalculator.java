package ru.snake.htdb.encoder;

import java.util.function.Function;

public class SizeCalculator {

	private int size;

	public SizeCalculator() {
		this.size = 0;
	}

	public SizeCalculator with(byte dummy) {
		size += Byte.BYTES;

		return this;
	}

	public SizeCalculator with(char dummy) {
		size += Character.BYTES;

		return this;
	}

	public SizeCalculator with(short dummy) {
		size += Short.BYTES;

		return this;
	}

	public SizeCalculator with(int dummy) {
		size += Integer.BYTES;

		return this;
	}

	public SizeCalculator with(long dummy) {
		size += Long.BYTES;

		return this;
	}

	public SizeCalculator with(Byte dummy) {
		size += Byte.BYTES;

		return this;
	}

	public SizeCalculator with(Character dummy) {
		size += Character.BYTES;

		return this;
	}

	public SizeCalculator with(Short dummy) {
		size += Short.BYTES;

		return this;
	}

	public SizeCalculator with(Integer dummy) {
		size += Integer.BYTES;

		return this;
	}

	public SizeCalculator with(Long dummy) {
		size += Long.BYTES;

		return this;
	}

	public SizeCalculator withLength(String value) {
		size += LengthEncoder.size(value);

		return this;
	}

	public SizeCalculator withZero(String value) {
		size += ZeroEncoder.size(value);

		return this;
	}

	public <T> SizeCalculator withNullable(T value, Function<T, SizeCalculator> sizeCallback) {
		size += 1;

		if (value != null) {
			size += sizeCallback.apply(value).size;
		}

		return this;
	}

	public <T> SizeCalculator withStruct(T value, Function<T, SizeCalculator> sizeCallback) {
		size += sizeCallback.apply(value).size;

		return this;
	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		return "SizeCalculator [size=" + size + "]";
	}

}
