package base.utils;

import java.util.HashMap;

/**
 * 哈希表
 * Created by MelloChan on 2018/1/13.
 */
public class HashDemo {
    public static void main(String[] args) {
        HashMap<String,String>hashMap=new HashMap<>(8);
        System.out.println(hashMap.get("key"));
        //运行传空键值对
        hashMap.put(null,null);
        hashMap.put("key","value");
        hashMap.put("key","value");
        System.out.println(hashMap.get("key"));
    }
}
