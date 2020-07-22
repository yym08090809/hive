package com.lcx.utils;

import org.apache.hadoop.hive.ql.exec.UDF;

//统计微博内容中出现iphone的次数
public class IphoneCount extends UDF {
    public static int evaluate(String strings) {
        String phone = "iphone";
        int count = 0;
        int index = 0;;
        while (index != -1) {
            index = strings.indexOf(phone,index);
            count++;
            if(index == -1)break;
            index = index + phone.length();
        }
        return count-1;
    }
}
