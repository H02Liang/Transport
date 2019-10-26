# Transport System
##项目进度记录
### Day01 XML配置版
* 以XML形式完成SpringBoot集成Hibernate
  1. hibernate.cfg.xml：Hibernate的主配置文件，是Spring获取Hibernate配置的入口（文件名固定，并且此文件默认放在src或resource目录下），用于配置数据源、数据映射文件和一些可选配置；

     ```
     <?xml version="1.0" encoding="utf-8"?>
     <!DOCTYPE hibernate-configuration PUBLIC
             "-//Hibernate/Hibernate Configuration DTD//EN"
             "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
     <hibernate-configuration>
         <session-factory>
             <!--配置数据源信息-->
             <property name="hibernate.connection.driver-class">com.mysql.cj.jdbc.Driver</property>
             <property name="hibernate.connection.url">jdbc:mysql:///transport?serverTimezone=Asia/Shanghai</property>
             <property name="hibernate.connection.username">root</property>
             <property name="hibernate.connection.password">root</property>
     
             <!--输出SQL的日志信息-->
             <property name="hibernate.show_sql">true</property>
             <!--格式化SQL语句的输出-->
             <property name="hibernate.format_sql">true</property>
             <!--指定hibernate中使用MySQL的语法格式-->
             <property name="hibernate.dialect">org.hibernate.dialect.MySQL5Dialect</property>
     
             <!--配置生成表的方式，update表示表不存在则创建，存在则更新-->
             <property name="hibernate.hbm2ddl.auto">update</property>
     
             <!--指定映射文件相对resource的路径-->
             <mapping resource="mapping/transport.hbm.xml" />
         </session-factory>
     </hibernate-configuration>
     ```

  2. transport.hbm.xml：数据映射文件，用于指定类属性与表列名的对应关系

     ```
     <?xml version="1.0" encoding="UTF-8"?>
     <!DOCTYPE hibernate-mapping PUBLIC
             "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
             "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
     <!--指定DAO的包路径-->
     <hibernate-mapping package="com.transport.system.dao">
         <!--默认生成表名，可以使用table属性指定表名-->
         <class name="Passenger">
             <id name="id">
                 <!--主键自动增长-->
                 <generator class="native" />
             </id>
             <!--默认生成列名-->
             <property name="name" />
             <property name="sex" />
             <property name="age" />
             <!--指定列名-->
             <property name="vipRank" column="vip_rank" />
             <property name="address" />
             <property name="remarks" />
             <property name="createTime" column="create_time" />
             <property name="updateTime" column="update_time" />
         </class>
     </hibernate-mapping>
     ```
* 完成简单的CRUD检验框架搭建可用性并简单学习HQL   
  HQL是建立在Hibernate上一个独有的数据库语句，以对象联合表，属性联合列名的方式写SQL的方式，所以下图中的位置分别表示对象声明和属性引用。
  ![hql001](.\images\hql001.png)
### Day02 注解配置版
* 以注解形式完成SpringBoot集成Hibernate（已将hibernate配置文件改名为hibernate.cfg11.xml）

  1. 首先在application的配置文件中设置数据源和一些其他可选配置；

     ```
     server:
       port: 8080
     
     spring:
       application:
         name: hibernateDemo
     
       datasource:
         driver-class-name: com.mysql.cj.jdbc.Driver
         url: jdbc:mysql:///transport?serverTimezone=Asia/Shanghai
         username: root
         password: root
     
       jpa:
         database: mysql
         hibernate:
           ddl-auto: update
           dialect: org.hibernate.dialect.MySQL5InnoDBDialect
         show-sql: true
     ```

  2. 在实体类中通过@Entity、@Table和@Colum等指定了数据库表列名与类属性之间的关联，在设置了@Table后即使不设置@Colum，类中属性名将以默认为本名的方式对应为表的列名。

     ```
     package com.transport.system.dao;
     
     import lombok.AllArgsConstructor;
     import lombok.Data;
     import lombok.NoArgsConstructor;
     import lombok.ToString;
     import lombok.experimental.Accessors;
     
     import javax.persistence.Column;
     import javax.persistence.Entity;
     import javax.persistence.GeneratedValue;
     import javax.persistence.GenerationType;
     import javax.persistence.Id;
     import javax.persistence.Table;
     import java.io.Serializable;
     
     /**
      * Class description 乘客类，记录乘客信息
      * @author LiangHang
      * @createTime 2019年10月26日01:40:23
      */
     @Data
     @Accessors(chain = true)
     @NoArgsConstructor
     @AllArgsConstructor
     @ToString
     @Entity
     @Table(name = "t_passenger")
     public class Passenger extends BaseEntity implements Serializable {
         private static final long serialVersionUID = -2261198871320755689L;
         /**
          * 乘客编号
          */
         @Id
         @GeneratedValue(strategy = GenerationType.AUTO)
         private int id;
         /**
          * 乘客姓名
          */
         private String name;
         /**
          * 身份证号码
          */
         @Column(name = "id_card_num")
         private String idCardNum;
         /**
          * 性别
          */
         private int sex;
         /**
          * 年龄
          */
         private int age;
         /**
          * 会员等级
          */
         @Column(name = "vip_rank")
         private int vipRank;
         /**
          * 常用住址
          */
         private String address;
     }
     ```

* 以MVC分层的方式最终实现了Hibernate读取数据库操作

  1. Controller层

     ```
     package com.transport.system.controller;
     
     import Passenger;
     import Ticket;
     import TicketService;
     import org.springframework.beans.factory.annotation.Autowired;
     import org.springframework.web.bind.annotation.RequestMapping;
     import org.springframework.web.bind.annotation.RestController;
     
     import javax.transaction.Transactional;
     import java.text.ParseException;
     import java.text.SimpleDateFormat;
     import java.util.Date;
     
     /**
      * Class description
      *
      * @author LiangHang
      * @createTime 2019/10/26 12:44
      */
     @RestController
     @RequestMapping("/ticket")
     public class TicketController {
     
         @Autowired
         private TicketService ticketService;
     
         @Transactional(rollbackOn = Exception.class)
         @RequestMapping("/save")
         public String saveTicket() {
             Ticket ticket = new Ticket();
             Date date = new Date();
             Passenger passenger = new Passenger();
             passenger.setName("张安光").setIdCardNum("440881199510192341").setSex(0).setAge(28).setVipRank(8)
                     .setAddress("廉江永兴100栋5楼").setRemarks("性情豪爽、乐观积极").setCreateTime(date).setUpdateTime(date);
             try {
                 ticket.setPassengerName(passenger.getName())
                         .setPassengerICNum(passenger.getIdCardNum()).setDepart("廉江永兴").setArrive("最近的小巷子")
                         .setDeptTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-10-26 16:00:00"))
                         .setPrice(600).setRemarks("玉林。。。").setCreateTime(date).setUpdateTime(date);
             } catch (ParseException e) {
                 e.printStackTrace();
             }
             ticketService.save(ticket);
             return "Save success!!\n"+ticket;
         }
     }
     ```

  2. Service层

     ```
     package com.transport.system.service.impl;
     
     import Ticket;
     import TicketRepository;
     import TicketService;
     import org.springframework.beans.factory.annotation.Autowired;
     import org.springframework.stereotype.Service;
     
     /**
      * Class description
      *
      * @author LiangHang
      * @createTime 2019/10/26 11:57
      */
     @Service
     public class TicketServiceImpl implements TicketService {
     
         @Autowired
         private TicketRepository ticketRepository;
     
         /**
          * 生成票据
          * @param ticket 
          */
         @Override
         public void save(Ticket ticket) {
             ticketRepository.save(ticket);
         }
     }
     ```

  3. Repository层

     ```
     package com.transport.system.repository;
     
     import Ticket;
     import org.springframework.data.jpa.repository.JpaRepository;
     import org.springframework.stereotype.Repository;
     
     /**
      * Class description
      *
      * @author LiangHang
      * @createTime 2019/10/26 11:54
      */
     @Repository
     public interface TicketRepository extends JpaRepository<Ticket,Integer> {
     
     }
     ```
* 接入日志系统
* 接入Swagger
### Day03 
