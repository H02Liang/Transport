package com.helloworld.transport;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class description
 *
 * @author LiangHang
 * @createTime 2019/12/9 20:09
 */
public class StreamTest {
    public static void main(String[] args) throws JsonProcessingException {
        List<Student> list = new ArrayList<>();
        list.add(new Student(1, "张1", 21, "Hello1"));
        list.add(new Student(2, "张2", 19, "Hello3"));
        list.add(new Student(2, "张2", 19, "Hello3"));
        list.add(new Student(2, "张2", 19, "Hello3"));
        list.add(new Student(3, "张3", 22, "Hello2"));
        list.add(new Student(4, "张4", 18, "Hello4"));

//        Stream<Student> stream = list.stream();
//        Stream<Student> streamFilter = stream.filter(stu -> stu.getAge() > 20);
//        streamFilter.forEach(student -> System.out.println(student));

//        list.stream()
//                .filter(stu -> stu.getAge() < 20).distinct()
//                .forEach(student -> System.out.println(student));

        list.stream().filter(student -> student.getAge() < 20).map(Student::getId).distinct().forEach(System.out::println);

        System.out.println(Stream.of("AA", "BB", "CC", "BB", "AA").distinct().collect(Collectors.joining(",")));

//        Stream.generate(Math::random).filter(db -> db>0.99999999).forEach(db -> System.out.println(db));

//        Stream.iterate(1, item -> item * 3).limit(4).forEach(System.out::println);

//        Stream.of("1", "2", "3").mapToInt(Integer::parseInt).forEach(System.out::println);

//        Stream.of(1, 2, 3, 4, 5)
//                .peek(integer -> System.out.println("accept:" + integer))
//                .forEach(System.out::println);

//        Stream.of(5, 4, 3, 2, 1)
//                .sorted()
//                .forEach(System.out::println);
//        // 打印结果
//        // 1，2，3,4,5

//        Stream.of(1, 2, 3, 4, 5)
//                .sorted((o1, o2) -> o2-o1)
//                .forEach(System.out::println);
//        // 打印结果
//        // 5, 4, 3, 2, 1

//        Stream.of(5,2,1,4,3).sorted().skip(2)
//                .forEachOrdered(integer -> System.out.println("integer:"+integer));

//        Stream.of(5,2,1,4,3).sorted(((o1, o2) -> o2-o1)).skip(2)
//                .forEach(integer -> System.out.println("integer:"+integer));

//        Stream.iterate(1, item -> item + 2).limit(5).forEach(System.out::println);

//        System.out.println(Stream.of(1, 2, 3, 4, 5).skip(2).max(((o1, o2) -> o2 - o1)).get());

//        System.out.println(Stream.of(1, 2, 3, 4).findAny().get());
    }
}

/**
 * 学生信息类
 */
class Student{
    private Integer id;
    private String name;
    private int age;
    private String remark;

    public Student(int id, String name, int age, String remark) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", remark='" + remark + '\'' +
                '}';
    }
}