package com.helloworld.transport;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Set类型
 */
class SetType {
    int i;
    public SetType (int n) {
        i = n;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof SetType && i == ((SetType) o).i;
    }

    @Override
    public String toString() {
        return Integer.toString(i);
    }
}

/**
 * Hash类型
 */
class HashType extends SetType{

    public HashType(int n) {
        super(n);
    }

    @Override
    public int hashCode() {
        return i;
    }
}

/**
 * Tree类型
 */
class TreeType extends SetType implements Comparable<TreeType> {

    public TreeType(int n) {
        super(n);
    }

    @Override
    public int compareTo(TreeType o) {
        return (o.i < i ? -1 : (o.i == i) ? 0 : 1);
    }
}
/**
 * Class description
 *
 * @author LiangHang
 * @createTime 2019/11/1 22:25
 */
public class TestType {
    /**
     * 向set填充type类型的元素并返回
     *
     * @param set
     * @param type
     * @param <T>
     * @return
     */
    static <T> Set<T> fill(Set<T> set, Class<T> type) {
        try {
            for (int i = 0; i < 10; i++) {
                boolean flag = set.add(type.getConstructor(int.class).newInstance(i));
                boolean a = flag;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return set;
    }

    /**
     * 尝试向set中填充type类型的重复元素并输出内容
     * @param set
     * @param type
     * @param <T>
     */
    static <T> void test(Set<T> set, Class<T> type) {
        fill(set, type);
        fill(set, type); // Try to add duplicas
        fill(set, type);
        System.out.println(set);
    }

    public static void main(String[] args) {
        /**
         * 前两组添加元素不能相同的原因在于：
         * HashSet在存储数据是会先计算HashCode
         * 两个元素的HashCode相同时则会根据equals()进行比较
         * 而HashType类型首先是继承了SetType的equals(),
         * 并且自定义了HashCode()，所以相同元素添加时返回false表示已存在相同元素内容
         */
        HashType n1 = new HashType(4);
        HashType n2 = new HashType(4);
        System.out.println(n1.equals(n2));
        test(new HashSet<HashType>(), HashType.class);
        test(new LinkedHashSet<HashType>(), HashType.class);
        /**
         * 这一组添加元素不能相同的原因在于：
         * TreeType继承了equals()并自定义了compareTo()
         * TreeSet在添加元素时只调用equals()判定元素是否相等
         */
        test(new TreeSet<TreeType>(), TreeType.class);
        /**
         * 下面四组都添加了元素值相同的原因在于：
         * HashSet与LinkedHashSet在添加元素都用到了HashCode
         * 而SetType与TreeType均没有对hashCode()做重写
         * 导致每次创建的新对象的HashCode的计算方式都沿用了
         * Object中HashCode的计算方式，也就是被默认为对象的地址值，
         * 所以每创建一次对象均添加了一个新的元素到Set集合中
         */
        test(new HashSet<SetType>(), SetType.class);
        test(new HashSet<TreeType>(), TreeType.class);
        test(new LinkedHashSet<SetType>(), SetType.class);
        test(new LinkedHashSet<TreeType>(), TreeType.class);
        /**
         * 下面两组报异常的原因在于：
         * SetType和HashType均没有实现Comparable接口
         * 而TreeSet集合做排序的前提是基于Comparable接口的
         */
        try {
            test(new TreeSet<SetType>(), SetType.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            test(new TreeSet<HashType>(), HashType.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
