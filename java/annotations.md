# Annotations

## 1.概念

Java注解是Java语言5.0版本开始支持加入源代码的特殊语法元数据。为我们在代码中添加信息提供了一种形式化的方法，使我们可以在稍后某个时刻非常方便的使用这些数据。
Java语言中的类、方法、变量、参数和包等都可以被标注。和Javadoc不同，Java标注可以通过反射获取注解内容。在编译器生成类文件时，注解可以被嵌入到字节码中。Java虚拟机可以保留注解内容，在运行时可以获取到注解内容。

## 2.内置注解

Java 定义了一套注解，共有 7 个，3 个在 java.lang 中，剩下 4 个在 java.lang.annotation 中。

## 2.1.作用在代码的注解是

* @Override - 检查该方法是否是重写方法。如果发现其父类，或者是引用的接口中并没有该方法时，会报编译错误。
* @Deprecated - 标记过时方法。如果使用该方法，会报编译警告。
* @SuppressWarnings - 指示编译器去忽略注解中声明的警告。

## 2.2.作用在其他注解的注解(或者说元注解)

@Retention - 标识这个注解怎么保存，是只在代码中，还是编入class文件中，或者是在运行时可以通过反射访问。
@Documented - 标记这些注解是否包含在用户文档中。
@Target - 标记这个注解应该是哪种 Java 成员。
@Inherited - 标记这个注解是继承于哪个注解类(默认 注解并没有继承于任何子类)

## 2.3.从 Java 7 开始，额外添加了 3 个注解

@SafeVarargs - Java 7 开始支持，忽略任何使用参数为泛型变量的方法或构造函数调用产生的警告。
@FunctionalInterface - Java 8 开始支持，标识一个匿名函数或函数式接口。
@Repeatable - Java 8 开始支持，标识某注解可以在同一个声明上使用多次。

## 3.自定义注解

### 3.1.元注解详解

**@Retention**

@Retention annotation指定标记注释的存储方式：

RetentionPolicy.SOURCE - 标记的注释仅保留在源级别中，并由编译器忽略。
RetentionPolicy.CLASS - 标记的注释在编译时由编译器保留，但Java虚拟机（JVM）会忽略。
RetentionPolicy.RUNTIME - 标记的注释由JVM保留，因此运行时环境可以使用它。

**@Documented**

@Documented 注释表明，无论何时使用指定的注释，都应使用Javadoc工具记录这些元素。（默认情况下，注释不包含在Javadoc中。）有关更多信息，请参阅 Javadoc工具页面。

**@Target**

@Target 注释标记另一个注释，以限制可以应用注释的Java元素类型。目标注释指定以下元素类型之一作为其值

* ElementType.TYPE 可以应用于类的任何元素。
* ElementType.FIELD 可以应用于字段或属性。
* ElementType.METHOD 可以应用于方法级注释。
* ElementType.PARAMETER 可以应用于方法的参数。
* ElementType.CONSTRUCTOR 可以应用于构造函数。
* ElementType.LOCAL_VARIABLE 可以应用于局部变量。
* ElementType.ANNOTATION_TYPE 可以应用于注释类型。
* ElementType.PACKAGE 可以应用于包声明。
* ElementType.TYPE_PARAMETER 可以用于泛型声明 class <@type_param_annotation T> MyList
* ElementType.TYPE_USE 可以用于泛型实例化  MyList<@type_use Person> mylistInstance = new MyList<>()...


**@Inherited**

@Inherited 注释表明注释类型可以从超类继承。当用户查询注释类型并且该类没有此类型的注释时，将查询类的超类以获取注释类型（默认情况下不是这样）。此注释仅适用于类声明。

**@Repeatable**

Repeatable Java SE 8中引入的，@Repeatable注释表明标记的注释可以多次应用于相同的声明或类型使用(即可以重复在同一个类、方法、属性等上使用)

### 3.2.自定义一个注解

```java
@Deprecated
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldName {
    String value() default "";
}
```

```java
if (!isEmpty(newValue)) {
    Map<String, Object> map = new HashMap<>();
    String newFieldName = newField.getName();
    
    //在这里获取注解的信息
    if (newField.isAnnotationPresent(FieldName.class)) {
        FieldName fieldNameAnno = newField.getAnnotation(FieldName.class);
        newFieldName = fieldNameAnno.name();
    }
        map.put(FIELD_NAME, newFieldName);
        map.put(OLD_VALUE, oldValue);
        map.put(NEW_VALUE, newValue);
        list.add(map);
}
```

### 3.3.ElementType.PACKAGE使用场景?

需要注解某个包

```
@Documented
@Target(value = {ElementType.PACKAGE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BoundedContext {}
```

java项目中提供 package-info.java 文件, 在package-info.java文件中引用已定义的包并添加注解

```
@BoundedContext
package com.clsaa.tiad.buidlingblock.annotation;
```

https://markdown-img-bed-common.oss-cn-hangzhou.aliyuncs.com/2020-02-13-161838.png


### 3.4.@Repeatable使用场景

@Repeatable是java8为了解决同一个注解不能重复在同一类/方法/属性上使用的问题。

如果注解中两个值存在依赖关系, @validate注解中bizcode和orderType是一对一的关系

```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
//@Repeatable(Validates.class)
public @interface Validate {

    /**
     * 业务类型
     * @return
     */
    String bizCode();

    /**
     * 订单类型
     * @return
     */
    int orderType();

}
```

希望添加如下注解

```java
@Validate(bizCode = "fruit",orderType = 1)
@Validate(bizCode = "fruit",orderType = 2)
@Validate(bizCode = "vegetable",orderType = 2)
public class BizLogic2 {
}
```

在java8中是不被允许的, 需要新建一个注解

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Validates {

    Validate[] value();

}
```

代码修改如下

```java
@Validates(value = {
        @Validate(bizCode = "fruit",orderType = 1)
        @Validate(bizCode = "fruit",orderType = 2)
        @Validate(bizCode = "vegetable",orderType = 2)
})
public class BizLogic2 {
}
```

@Repeatable出来之后，我们在不改动@Validates的情况下，对@Validate进行修改，增加@Repeatable(Validates.class)

```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(Validates.class)
public @interface Validate {

    /**
     * 业务类型
     * @return
     */
    String bizCode();

    /**
     * 订单类型
     * @return
     */
    int orderType();
}
```

原理, 反编译后代码

```java
/*@Validates(value = {*/
        @Validate(bizCode = "fruit",orderType = 1)
        @Validate(bizCode = "fruit",orderType = 2)
        @Validate(bizCode = "vegetable",orderType = 2)
/*})*/
public class BizLogic2 {
}
```

取值,兼容逻辑如下

```java
Validate validate = AnnotationUtils.getAnnotation(BizLogic.class,Validate.class);
        if(Objects.nonNull(validate)){
            System.out.println(validate.bizCode());
        }
        Validates validates = AnnotationUtils.getAnnotation(BizLogic2.class,Validates.class);
        Arrays.stream(validates.value()).forEach(a->{
            System.out.println(a.bizCode());
        });
```

引用:

<https://www.jianshu.com/p/4f65fae2510b>
<https://www.cnblogs.com/jajian/p/9695055.html>