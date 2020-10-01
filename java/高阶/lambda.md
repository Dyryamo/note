# Lambda表达式定义
- 面向过程程序语言：参数传递是基本类型的变量
- 面向对象语言
    - 传递基本类型的变量
    - 传递对象变量
- 传递方法/代码块(函数式程序语言设计)
    - 刚开始，Java为了简单性、一致性，拒绝此功能
    - 为了市场和技术的需要，Java 8开始，支持此项功能，提出Java的Lambda表达式实现
    - 在计算机编程中，通常用来表示一个匿名函数
    - Lambda表达式可以当作参数，传递给其他高阶函数
# Lambda表达式形式
```
(参数) -> 表达式
(String first, String second) -> first.length() - second.length();

(参数) -> { 多条语句}

(first, second) - > {
    //形参类型可以不写，通过上下文推断出形式参数类型
    int result = (-1)  * first.length() - second.length();
    return result;
}
```
# Lambda表达式
- 类似于匿名方法，一个没有名字的方法
- 参数，箭头，表达式语句
- 可以忽略写参数类型
- 坚决不声明返回值类型
- 没有public/protected/private/static/final等修饰符
- 单句表达式，将直接返回值，不用大括号
- 带return语句， 算多句，必须用大括号
# Lambda表达式
- 如果有返回值，返回值类型会在上下文推断出来的，无需声明
- 只在某几个分支有返回值，这样是不合法的