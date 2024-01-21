# Sealed Class

(from chatgpt)

## 简述

Java 中的 sealed 类是 Java 15 引入的一项功能，用于限制哪些其他类或接口可以扩展或实现该类。这是一种更精确控制继承的方式，允许类的设计者明确指定哪些类是允许的子类，从而提供了一种更严格的类型层次结构控制。

- sealed 
  - v. 密封(容器);封上(信封);封盖…的表面 
  - adj. 密封的；未知的

### 特点

1. 限制继承：只有特定的类可以扩展 sealed 类。
2. 声明权限：sealed 类需要在声明中列出所有允许的子类。
3. 灵活性：允许的子类可以是 final 的、非 final 的（但仍然是 sealed 的）或 non-sealed 的。

### 示例

下面是一个 sealed 类的简单示例：

```java
public sealed class Shape
permits Circle, Rectangle, Square {
    // 类的实现
}

final class Circle extends Shape {
// Circle 的实现
}

final class Rectangle extends Shape {
// Rectangle 的实现
}

non-sealed class Square extends Shape {
// Square 的实现
}
```

在这个例子中，Shape 是一个 sealed 类，它只允许 Circle、Rectangle 和 Square 这三个类继承它。Circle 和 Rectangle 被声明为 final，这意味着它们不能被进一步扩展。而 Square 被声明为 non-sealed，这意味着其他类可以自由地扩展 Square。

## 目的

sealed 类的引入主要是为了提供更精确的控制和更清晰的表达设计意图。
它们特别适用于模式匹配和代数数据类型（ADT）的场景，在这些场景中，对类型层次结构的完整性和严格性有明确的要求。

## 使用 sealed 类的注意事项

版本兼容性：sealed 类是 Java 15 中引入的，因此在更早版本的 Java 中不可用。
设计决策：使用 sealed 类时，需要仔细考虑继承结构，以确保它们符合你的设计目标和约束。
模式匹配：sealed 类与 Java 中新引入的模式匹配特性很好地配合，使得在 switch 表达式或语句中对类型进行匹配变得更加安全和直观。


