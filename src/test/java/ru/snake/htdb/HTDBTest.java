package ru.snake.htdb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import ru.snake.htdb.serialize.Serializer;
import ru.snake.htdb.serialize.StringSerializer;

public class HTDBTest {

	@Test
	public void getMustWork() throws Exception {
		Serializer<String> serializer = new StringSerializer();
		HTDB<String, String, String> htdb = HTDB.create(serializer);

		htdb.put("1", "1", "1");

		assertEquals("1", htdb.get("1", "1"));

		htdb.close();
	}

	@Test
	public void putMustWork() throws Exception {
		Serializer<String> serializer = new StringSerializer();
		HTDB<String, String, String> htdb = HTDB.create(serializer);

		assertEquals(true, htdb.put("1", "1", "1"));
		assertEquals(false, htdb.put("1", "1", "1"));

		htdb.close();
	}

	@Test
	public void containsMustWork() throws Exception {
		Serializer<String> serializer = new StringSerializer();
		HTDB<String, String, String> htdb = HTDB.create(serializer);

		htdb.put("1", "1", "1");

		assertEquals(true, htdb.contains("1", "1"));
		assertEquals(false, htdb.contains("1", "2"));

		htdb.close();
	}

	@Test
	public void deleteMustWork() throws Exception {
		Serializer<String> serializer = new StringSerializer();
		HTDB<String, String, String> htdb = HTDB.create(serializer);

		htdb.put("1", "1", "1");

		assertEquals(true, htdb.delete("1", "1"));
		assertEquals(false, htdb.delete("1", "1"));

		htdb.close();
	}

	@Test
	public void rangeMustWork() throws Exception {
		Serializer<String> serializer = new StringSerializer();
		HTDB<String, String, String> htdb = HTDB.create(serializer);
		List<String> result = new ArrayList<>();

		htdb.put("1", "1", "1");
		htdb.range("1", "0", "2", (k, v) -> result.add(k + "=>" + v));
		htdb.close();

		assertEquals(Arrays.asList("1=>1"), result);
	}

	@Test
	public void succMustWork() throws Exception {
		Serializer<String> serializer = new StringSerializer();
		HTDB<String, String, String> htdb = HTDB.create(serializer);

		htdb.put("1", "1", "1");
		htdb.put("1", "2", "2");

		assertEquals("2", htdb.succ("1", "1").getKey());

		htdb.close();
	}

	@Test
	public void predMustWork() throws Exception {
		Serializer<String> serializer = new StringSerializer();
		HTDB<String, String, String> htdb = HTDB.create(serializer);

		htdb.put("1", "1", "1");
		htdb.put("1", "2", "2");

		assertEquals("1", htdb.pred("1", "2").getKey());

		htdb.close();
	}

	@Test
	public void countMustWork() throws Exception {
		Serializer<String> serializer = new StringSerializer();
		HTDB<String, String, String> htdb = HTDB.create(serializer);

		assertEquals(0, htdb.count());

		htdb.put("1", "1", "1");

		assertEquals(1, htdb.count());

		htdb.close();
	}

}
