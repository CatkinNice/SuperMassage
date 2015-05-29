package org.catkin.supermassage.utils;

import java.io.File;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.core.io.ClassPathResource;

/**
 * ID成器
 * 
 * @author Catkin_nice
 *
 */
public class Sequence {

	// 应用层缓存的序列号数量，可以提升序列号发放的性能，不用频繁更新数据库。
	private static final int defaultCacheSize = 0;

    private static int count = defaultCacheSize + 1;
    private static long index = 0;

    private static long getSequenceValue()
    {
    	long seq = 0;
    	try {
//    		File file = new ClassPathResource("/property/sequence.id").getFile();
//    		String str = FileUtils.readFileToString(file);
//        	seq = Long.parseLong(str.trim());
//        	FileUtils.write(file, String.valueOf(seq + defaultCacheSize));
//        	Logs.getLogger().debug("getSequenceValue:" + seq + ", cacheSize:" + defaultCacheSize);
		} catch (Exception ex) {
		}
        return seq;
    }

    public synchronized static long getNextId() {
        // 如果发放的序列号小于缓存的个数，则通过缓存发放一个序列号
        if (count < defaultCacheSize) {
            count++;
            index++;
        } else {
            // 获取一组序列号，缓存
            index = getSequenceValue();
            count = 1;
        }

//        long seq = index * 1000 + ConfigUtil.getConfig().getServerId();
        return 0;
    }
    
    public static void main(String[] args) {
		
	}

}
