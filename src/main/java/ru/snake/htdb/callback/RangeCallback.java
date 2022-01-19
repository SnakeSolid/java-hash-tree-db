package ru.snake.htdb.callback;

@FunctionalInterface
public interface RangeCallback<K, V> {

	/**
	 * Accept next key/value pair. Returns true if next element required, false
	 * to break iteration.
	 *
	 * @param key
	 *            encoded key
	 * @param value
	 *            encoded value
	 * @return true if next element required, false to break iteration
	 */
	public boolean accept(K key, V value);

}
