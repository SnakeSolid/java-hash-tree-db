# Hash Tree Database (HTDB)

This project is Java wrapper of HTDB library. HTDB is simple key-value storage implemented over `HashMap` and
`BTreeMap` collections, see [this project](https://github.com/SnakeSolid/rust-hash-tree-db)). This library provides
simple way to convert byte arrays to Java objects and vise versa.

## Usage

HTDB uses three basic parts to access data - partitions, keys and values. Partition is large section of data which
similar to `TreeMap`. Partition can contain only unique key values. Different partitions can contains the same key.
Keys and values are similar to `HashMap`.

Only serializer implementation is required to create HTDB with default configuration.

```java
Serializer<String> serializer = new StringSerializer();
HTDB<String, String, String> htdb = HTDB.create(serializer);
// operations with database
htdb.close();
```

HTDB implements `AutoCloseable` imterface and can be used in try with resources clause:

```java
Serializer<String> serializer = new StringSerializer();
try (HTDB<String, String, String> htdb = HTDB.create(serializer)) {
	// operations with database
}
```

All basic parts (partitions, keys and values) can use different serializers. Library already provide serialiser for
simple data types such as `Integer` and `String`. More complex serializers can be implemented inherit from
`AbstractSerializer` and using helpers from `ru.snake.htdb.encoder`.

## License

This project is licensed under the MIT License.
