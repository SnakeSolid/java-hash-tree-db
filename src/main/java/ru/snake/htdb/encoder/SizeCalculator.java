package ru.snake.htdb.encoder;

import java.util.function.BiFunction;

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

	public SizeCalculator with(float dummy) {
		size += Float.BYTES;

		return this;
	}

	public SizeCalculator with(double dummy) {
		size += Double.BYTES;

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

	public SizeCalculator with(Float dummy) {
		size += Float.BYTES;

		return this;
	}

	public SizeCalculator with(Double dummy) {
		size += Double.BYTES;

		return this;
	}

	public SizeCalculator withLength(String value) {
		size += Integer.BYTES + value.getBytes().length;

		return this;
	}

	public SizeCalculator withLength(byte[] value) {
		size += Integer.BYTES + value.length;

		return this;
	}

	public SizeCalculator withZero(String value) {
		size += value.getBytes().length + Byte.BYTES;

		return this;
	}

	public SizeCalculator withZero(byte[] value) {
		size += value.length + Byte.BYTES;

		return this;
	}

	public <T> SizeCalculator withNullable(T value, BiFunction<SizeCalculator, T, SizeCalculator> sizeCallback) {
		size += 1;

		if (value != null) {
			sizeCallback.apply(this, value);
		}

		return this;
	}

	public <T> SizeCalculator withStruct(T value, BiFunction<SizeCalculator, T, SizeCalculator> sizeCallback) {
		sizeCallback.apply(this, value);

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
