package ru.snake.htdb;

import java.io.IOException;

import ru.snake.htdb.callback.RangeCallbackNative;
import ru.snake.htdb.entry.RawEntry;

public class HTDBNative {

	static {
		try {
			LibraryLoader.loadLibrary("htdb_jni");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static native long create(int pageSize, int nPages, String storagePath);

	public static native byte[] get(long handle, byte[] hash, byte[] key);

	public static native boolean put(long handle, byte[] hash, byte[] key, byte[] value);

	public static native boolean contains(long handle, byte[] hash, byte[] key);

	public static native boolean delete(long handle, byte[] hash, byte[] key);

	public static native void
			range(long handle, byte[] hash, byte[] keyFirst, byte[] keyLast, RangeCallbackNative callback);

	public static native RawEntry succ(long handle, byte[] hash, byte[] key);

	public static native RawEntry pred(long handle, byte[] hash, byte[] key);

	public static native long count(long handle);

	public static native void save(long handle);

	public static native void load(long handle);

	public static native long destroy(long handle);

}
