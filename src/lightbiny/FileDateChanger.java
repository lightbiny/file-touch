package lightbiny;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.regex.Pattern;

public class FileDateChanger {
	public static void main(String[] args) throws Exception {
		if (args == null || args.length < 1) {
			System.out.println("Usage : java -jar [jar file] [path]");
		}
		
		Path path = Paths.get(args[0]);
		System.out.println("start with:  " + path.toString());
		
		Files.walkFileTree(path, new FileVisitor<Path>() {
			Pattern p = Pattern.compile(".(png|jpg|jpeg)$", Pattern.CASE_INSENSITIVE);

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				System.out.println(file.toString());
				if (p.matcher(file.toString()).find()) {
					File f = file.toFile();
					Path dest = Paths.get(file.getParent().toString(), file.getFileName().toString().replaceAll(":", "_"));
					System.out.println(dest.toString());
					System.out.println(f.renameTo(dest.toFile()));
				}
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				System.out.println(file.toString() + " failed.");
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				return FileVisitResult.CONTINUE;
			}
		});
		
		System.out.println("Finished.");
	}
}
