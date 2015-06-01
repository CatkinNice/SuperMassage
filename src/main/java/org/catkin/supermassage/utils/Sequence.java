package org.catkin.supermassage.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

/**
 * ID成器
 * 
 * @author Catkin_nice
 *
 */
public class Sequence {

	// 应用层缓存的序列号数量，可以提升序列号发放的性能，不用频繁更新数据库。
	private static final int CACHE_SIZE = 1000;

    private static int count = CACHE_SIZE;
    private static long index = 1;

	private static void getSequenceValue() {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			File file = new File(System.getProperty("user.dir"), "sequence.id");
			if (file.exists() && file.canRead()) {
				br = new BufferedReader(new FileReader(file));
				String localId = br.readLine();
				index = localId == null ? 1 : Long.valueOf(localId.trim());
			}

			bw = new BufferedWriter(new FileWriter(file));
			bw.write(String.valueOf(index + CACHE_SIZE));
			Log.info("getSequenceValue:" + index + ", cacheSize:" + CACHE_SIZE);
		} catch (Exception ex) {
			Log.error("[getSequenceValue]", ex);
		} finally {
			try {
				if (br != null) br.close();
				if (bw != null) bw.close();
			} catch (Exception e) {
			}
		}
	}

	public synchronized static long getNextId() {
		// 如果发放的序列号小于缓存的个数，则通过缓存发放一个序列号
		if (count < CACHE_SIZE) {
			count++;
			index++;
		} else {		// 获取一组序列号，缓存
			count = 1;
			getSequenceValue();
		}

//		long id = index * 100 + ConfigUtil.getConfig().getServerId(); //集群方案
		return index;
	}
	
}
