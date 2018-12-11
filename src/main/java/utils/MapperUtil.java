package utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class MapperUtil {

    private MapperUtil(){}

    /**
     *
     * @param t
     * @param sClass
     * @param <T>
     * @param <S>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T,S> S coverBean2AnotherBean(T t,Class<S> sClass) throws IllegalAccessException, InstantiationException {
        S s =sClass.newInstance();
        BeanUtils.copyProperties(t,s);
        return s;
    }

    /**
     *
     * @param tList
     * @param s
     * @param <T> 入参类型
     * @param <S> 出参类型
     * @return
     */
    public static <T,S>List<S> mapList2List(List<T> tList,Class<S> s){

        return tList.parallelStream().map(t -> {
            try {
                S s1 = coverBean2AnotherBean(t,s);
                return s1;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }

}
