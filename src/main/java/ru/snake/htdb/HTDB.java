package ru.snake.htdb;

import ru.snake.htdb.callback.RangeCallback;
import ru.snake.htdb.entry.Entry;
import ru.snake.htdb.entry.RawEntry;
import ru.snake.htdb.serialize.Serializer;

public class HTDB<P, K, V> implements AutoCloseable {

	private final Serializer<P> partitionSerializer;

	private final Serializer<K> keySerializer;

	private final Serializer<V> valueSerializer;

	private long handle;

	private HTDB(
		HTDBConfig conig,
		Serializer<P> partitionSerializer,
		Serializer<K> keySerializer,
		Serializer<V> valueSerializer
	) {
		int pageSize = conig.getPageSize();
		Integer nPages = conig.getNPages().orElseGet(() -> 0);
		String storagePath = conig.getStoragePath().getAbsolutePath();

		this.partitionSerializer = partitionSerializer;
		this.keySerializer = keySerializer;
		this.valueSerializer = valueSerializer;
		this.handle = HTDBNative.create(pageSize, nPages, storagePath);
	}

	public V get(P partition, K key) {
		byte[] partitionBytes = partitionSerializer.serialize(partition);
		byte[] keyBytes = keySerializer.serialize(key);

		return valueSerializer.deserialize(HTDBNative.get(handle, partitionBytes, keyBytes));
	}

	public boolean put(P partition, K key, V value) {
		byte[] partitionBytes = partitionSerializer.serialize(partition);
		byte[] keyBytes = keySerializer.serialize(key);
		byte[] valueBytes = valueSerializer.serialize(value);

		return HTDBNative.put(handle, partitionBytes, keyBytes, valueBytes);
	}

	public boolean contains(P partition, K key) {
		byte[] partitionBytes = partitionSerializer.serialize(partition);
		byte[] keyBytes = keySerializer.serialize(key);

		return HTDBNative.contains(handle, partitionBytes, keyBytes);
	}

	public boolean delete(P partition, K key) {
		byte[] partitionBytes = partitionSerializer.serialize(partition);
		byte[] keyBytes = keySerializer.serialize(key);

		return HTDBNative.delete(handle, partitionBytes, keyBytes);
	}

	public void range(P partition, K keyFirst, K keyLast, RangeCallback<K, V> callback) {
		byte[] partitionBytes = partitionSerializer.serialize(partition);
		byte[] keyFirstBytes = keySerializer.serialize(keyFirst);
		byte[] keyLastBytes = keySerializer.serialize(keyLast);

		HTDBNative.range(
			handle,
			partitionBytes,
			keyFirstBytes,
			keyLastBytes,
			(key, value) -> callback.accept(keySerializer.deserialize(key), valueSerializer.deserialize(value))
		);
	}

	public Entry<K, V> succ(P partition, K key) {
		byte[] partitionBytes = partitionSerializer.serialize(partition);
		byte[] keyBytes = keySerializer.serialize(key);

		RawEntry entry = HTDBNative.succ(handle, partitionBytes, keyBytes);

		if (entry != null) {
			return new Entry<>(
				keySerializer.deserialize(entry.getKey()),
				valueSerializer.deserialize(entry.getValue())
			);
		} else {
			return null;
		}
	}

	public Entry<K, V> pred(P partition, K key) {
		byte[] partitionBytes = partitionSerializer.serialize(partition);
		byte[] keyBytes = keySerializer.serialize(key);

		RawEntry entry = HTDBNative.pred(handle, partitionBytes, keyBytes);

		if (entry != null) {
			return new Entry<>(
				keySerializer.deserialize(entry.getKey()),
				valueSerializer.deserialize(entry.getValue())
			);
		} else {
			return null;
		}
	}

	public long count() {
		return HTDBNative.count(handle);
	}

	@Override
	public void close() throws Exception {
		HTDBNative.destroy(handle);

		handle = 0;
	}

	public static <T> HTDB<T, T, T> create(Serializer<T> serializer) {
		return new HTDB<>(new HTDBConfig(), serializer, serializer, serializer);
	}

	public static <T> HTDB<T, T, T> create(HTDBConfig config, Serializer<T> serializer) {
		return new HTDB<>(config, serializer, serializer, serializer);
	}

	public static <P, K, V> HTDB<P, K, V>
			create(Serializer<P> partitionSerializer, Serializer<K> keySerializer, Serializer<V> valueSerializer) {
		return new HTDB<>(new HTDBConfig(), partitionSerializer, keySerializer, valueSerializer);
	}

	public static <P, K, V> HTDB<P, K, V> create(
		HTDBConfig config,
		Serializer<P> partitionSerializer,
		Serializer<K> keySerializer,
		Serializer<V> valueSerializer
	) {
		return new HTDB<>(config, partitionSerializer, keySerializer, valueSerializer);
	}

	@Override
	public String toString() {
		return "HTDB [handle=" + handle + ", partitionSerializer=" + partitionSerializer + ", keySerializer="
				+ keySerializer + ", valueSerializer=" + valueSerializer + "]";
	}

}
