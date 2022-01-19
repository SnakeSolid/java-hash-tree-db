package ru.snake.htdb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class LibraryLoader {

	public static void loadLibrary(String libraryName) throws IOException {
		String library = System.mapLibraryName(libraryName);
		Path driverRoot = Files.createTempDirectory("htdb-");
		File driverDirectory = driverRoot.toFile();
		driverDirectory.deleteOnExit();

		InputStream resource = ClassLoader.getSystemResourceAsStream("driver/" + library);
		File driverPath = new File(driverDirectory, library);
		driverPath.deleteOnExit();

		try (FileOutputStream stream = new FileOutputStream(driverPath)) {
			byte[] buffer = new byte[8192];

			do {
				int length = resource.read(buffer);

				if (length == -1) {
					break;
				}

				stream.write(buffer, 0, length);
			} while (true);
		}

		System.load(driverPath.getAbsolutePath());
	}

}
