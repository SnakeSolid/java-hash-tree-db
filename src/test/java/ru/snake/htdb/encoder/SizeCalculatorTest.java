package ru.snake.htdb.encoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SizeCalculatorTest {

	@Test
	public void sizeMustReturnByteSize() {
		int result = new SizeCalculator().with((byte) 0).size();

		assertEquals(1, result);
	}

	@Test
	public void sizeMustReturnCharSize() {
		int result = new SizeCalculator().with((char) 0).size();

		assertEquals(2, result);
	}

	@Test
	public void sizeMustReturnShortSize() {
		int result = new SizeCalculator().with((short) 0).size();

		assertEquals(2, result);
	}

	@Test
	public void sizeMustReturnIntSize() {
		int result = new SizeCalculator().with((int) 0).size();

		assertEquals(4, result);
	}

	@Test
	public void sizeMustReturnLongSize() {
		int result = new SizeCalculator().with((long) 0).size();

		assertEquals(8, result);
	}

	@Test
	public void sizeMustReturnFloatSize() {
		int result = new SizeCalculator().with((float) 0).size();

		assertEquals(4, result);
	}

	@Test
	public void sizeMustReturnDoubleSize() {
		int result = new SizeCalculator().with((double) 0).size();

		assertEquals(8, result);
	}

	@Test
	public void sizeMustReturnStringLengthSize() {
		int result = new SizeCalculator().withLength("test").size();

		assertEquals(8, result);
	}

	@Test
	public void sizeMustReturnStringZeroSize() {
		int result = new SizeCalculator().withZero("test").size();

		assertEquals(5, result);
	}

	@Test
	public void sizeMustReturnValueSize() {
		int result = new SizeCalculator().withNullable("test", (s, v) -> s.withLength(v)).size();

		assertEquals(9, result);
	}

	@Test
	public void sizeMustReturnNullSize() {
		int result = new SizeCalculator().withNullable(null, (SizeCalculator s, String v) -> s.withLength(v)).size();

		assertEquals(1, result);
	}

}
