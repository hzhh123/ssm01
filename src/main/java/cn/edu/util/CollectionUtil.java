package cn.edu.util;

import java.util.*;

/**
 * Created by Administrator on 2017/8/1.
 */
public class CollectionUtil<T> {

    public  List<T>Ulist(List<T>list){
        List<T>result=new ArrayList<T>();
        Set<T> set = new LinkedHashSet<T>();
        set.addAll(list);
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            result.add(it.next());
        }
        return result;
    }
}
