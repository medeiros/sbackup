package br.com.develcraft.sbackup

import java.nio.file.{Files, Paths, FileVisitOption}
import java.util.EnumSet

class Backup() {
	def backup(source: String, destination: String) {
		Files.walkFileTree(Paths.get(source), EnumSet.of(FileVisitOption.FOLLOW_LINKS), 
			Integer.MAX_VALUE, new CopyDirVisitor(source, destination))
	}
}

object Backup extends App {
	new Backup().backup(args(0), args(1))
}
