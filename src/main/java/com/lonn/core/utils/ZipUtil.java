package com.lonn.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
	private static ArrayList<File> files;
	private static ArrayList<File> allfiles;

	/**
	 * 文件名为当前时间.zip
	 * 
	 * @param path
	 *            需要压缩的文件(文件夹)路径
	 *            例：Environment.getExternalStorageDirectory() + "/yyjoy/voice_temp.amr",
	 * @param outDirectory
	 *            输出的目录
	 *            例：Environment.getExternalStorageDirectory() + "/yyjoy/
	 * @param dir
	 *            是否带文件的完整路径
	 *            例：false 为压缩包内只有文件
	 * @param delete
	 *            删除标记
	 *            例: true 压缩后删除已经压缩过的文件
	 * @return 压缩后的文件
	 * 
	 */
	public static File getZipFile(String path, String outDirectory, boolean dir, boolean delete) {
		File file = new File(path);
		File resFile = new File(outDirectory);
		File result = null;
		if (!file.exists()) {
			return null;
		} else {
			try {
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
				String time = format
						.format(new Date(System.currentTimeMillis()));
				File outFile = new File(resFile.getAbsolutePath()
						+ File.separator + time + ".zip");
				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(outFile), 1024 * 1024);
				ZipOutputStream zipos = new ZipOutputStream(bos);
				if (file.isDirectory()) {
					files = new ArrayList<File>();
					allfiles = new ArrayList<File>();
					getAllFile(file);
					for (File f : files) {
						if (f.getName().equals(outFile.getName())) {
							continue;
						} else {
							result = file2Zip(f, resFile, zipos, dir, file);
							if (null == result)
								continue;
						}
					}
				} else if (file.isFile()) {
					result = file2Zip(file, resFile, zipos, dir, file);
				}
				if (delete)
					for (File f : allfiles) {
						f.delete();
					}
				zipos.flush();
				zipos.close();
			} catch (FileNotFoundException e) {
				return null;
			} catch (IOException e) {
				return null;
			}
		}

		if (result != null && resFile.exists())
			return result;
		return null;
	}

	private static File file2Zip(File file, File result, ZipOutputStream zipos,
			boolean dir, File oriFile) {
		try {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(file));
			String entry = "";
			if (oriFile.isDirectory()) {
				if (dir) {
					// entry = file.getParentFile().getPath() + File.separator
					// + file.getName();
					entry = file.getParentFile().getPath()
							.replace(oriFile.getParent(), "")
							+ File.separator + file.getName();
				} else {
					entry = file.getParentFile().getPath()
							.replace(oriFile.getPath(), "")
							+ File.separator + file.getName();
					if (String.valueOf(entry.charAt(0)).equals("\\")) {
						entry = entry.replaceFirst("\\\\", "");
					}
				}
			} else if (oriFile.isFile()) {
				entry = file.getName();
				if (dir)
					entry = file.getParentFile().getName() + File.separator
							+ file.getName();
			}
			zipos.putNextEntry(new ZipEntry(entry));
			int len;
			byte[] buffer = new byte[1024 * 1024];
			while ((len = bis.read(buffer)) != -1) {
				zipos.write(buffer, 0, len);
			}
			bis.close();
			zipos.flush();
			zipos.closeEntry();
			return result;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static void getAllFile(File file) {
		for (File f : file.listFiles()) {
			if (f.isDirectory()) {
				getAllFile(f);
			} else {
				files.add(f);
			}
			allfiles.add(f);
		}
	}




	
	public static void main(String[] args) {
		File f = getZipFile("D:\\log", "D:\\log", true, false);
		System.out.println(f);
	}
	
}
