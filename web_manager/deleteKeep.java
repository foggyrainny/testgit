import java.io.File;

public class deleteKeep {

	public static void main(String[] args) {
		String path = getRealPath();
		File file = new File(path);
		try {
			traversalAllFolder(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 遍历当前文件夹下的所有文件夹，对空的文件夹创建.keep文件
	 * 
	 * @param dir
	 * @throws Exception
	 */
	final static void traversalAllFolder(File dir) throws Exception {
		File[] fs = dir.listFiles();
		int fsLength = fs.length;
		for (int i = 0; i < fsLength; i++) {
			String filename = getFilename(fs[i].getPath());
			if (filename.equals(".keep")) {
				fs[i].delete();
			}
			if (fs[i].isDirectory()) {
				try {
					traversalAllFolder(fs[i]);
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * 获取当前jar包所在路径
	 * 
	 * @return
	 */
	public static String getRealPath() {
		String realPath = CreateKeep.class.getClassLoader().getResource("")
				.getFile();
		java.io.File file = new java.io.File(realPath);
		realPath = file.getAbsolutePath();
		try {
			realPath = java.net.URLDecoder.decode(realPath, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return realPath;
	}

	/**
	 * 获取文件名
	 * 
	 * @param
	 * @return
	 */
	public static String getFilename(String filepath) {
		if ((filepath != null) && (filepath.length() > 0)) {
			int dot = filepath.lastIndexOf('\\');
			if ((dot > -1) && (dot < (filepath.length() - 1))) {
				return filepath.substring(dot + 1);
			}
		}
		return "";
	}

}
