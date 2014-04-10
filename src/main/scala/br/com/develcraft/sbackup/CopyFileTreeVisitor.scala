package br.com.develcraft.sbackup

import java.nio.file.{CopyOption, Paths, Path, StandardCopyOption}
import java.nio.file.{Files, SimpleFileVisitor, FileVisitOption, FileVisitResult}
import java.nio.file.attribute.BasicFileAttributes

class CopyDirVisitor(source: String, destination: String) extends SimpleFileVisitor[Path] {
	def from = Paths.get(source)
	def to = Paths.get(destination)

	override def preVisitDirectory(dir: Path, attrs: BasicFileAttributes) : FileVisitResult = {
		val targetPath = to.resolve(from.relativize(dir))
		if (!Files.exists(targetPath)) {
			Files.createDirectory(targetPath)
		}
		return FileVisitResult.CONTINUE
	}

	override def visitFile(file: Path, attrs: BasicFileAttributes) : FileVisitResult = {
		Files.copy(file, to.resolve(from.relativize(file)), StandardCopyOption.REPLACE_EXISTING)
		return FileVisitResult.CONTINUE
	}

}
