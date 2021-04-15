package com.github.rumoel.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class FileMoverByExt {
	static File inputDir;

	public static void main(String[] args) {
		if (args.length == 1) {
			inputDir = new File(args[0]);
		} else {
			inputDir = new File(new File("").getAbsolutePath());
		}
		if (inputDir.isDirectory()) {
			recMoveByExt(inputDir);
		} else {
			throw new IllegalArgumentException(String.format("input arg NOT dir:%s", inputDir.getAbsolutePath()));
		}
	}

	private static void recMoveByExt(File inputDir) {
		File[] files = inputDir.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				try {
					moveByExt(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void moveByExt(File file) throws IOException {
		String ext = FilenameUtils.getExtension(file.getName());
		if (ext.length() > 0) {
			File destDir = new File(inputDir, ext);
			FileUtils.moveToDirectory(file, destDir, true);
		}
	}
}
