package cn.edu.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/26.
 */
public class StringUtil {
    public static List<Integer>toList(String str){
        List<Integer>ilist=new ArrayList<Integer>();
        String[] ss=str.split(",");
        for (String s:ss){
            ilist.add(Integer.parseInt(s));
        }
        return ilist;
    }
}
