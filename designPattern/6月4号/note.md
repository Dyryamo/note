## 接口隔离原则
    客户端不应该依赖他不需要的接口，即一个类对另一个类的依赖应该建立在最小的接口上
### 类图
### 代码实现
```
package com.ctgu.Date6_4;

public class segregation {
    public static void main(String args[]){
        A a = new A();
        a.depend1(new B());
        a.depend2(new B());
        a.depend3(new B());

        C c = new C();
        c.depend1(new D());
        c.depend2(new D());
        c.depend3(new D());
    }
}

package com.ctgu.Date6_4;

public interface Interface1 {
    public void operation1();
}

package com.ctgu.Date6_4;

public interface Interface2 {
    public void operation2();
    public void opearation3();
}

package com.ctgu.Date6_4;

public interface Interface3 {
    public void operation4();
    public void operation5();
}

package com.ctgu.Date6_4;

public class A {
    public void depend1(Interface1 i){
        i.operation1();
    }

    public void depend2(Interface2 i){
        i.operation2();
    }

    public void depend3(Interface2 i){
        i.opearation3();
    }
}

package com.ctgu.Date6_4;

public class C {
    public void depend1(Interface1 i){
        i.operation1();
    }

    public void depend2(Interface3 i){
        i.operation4();
    }

    public void depend3(Interface3 i){
        i.operation5();
    }
}

package com.ctgu.Date6_4;

public class B implements Interface1,Interface2 {

    @Override
    public void operation1() {
        System.out.println("B 实现了Operation1 ");
    }

    @Override
    public void operation2() {
        System.out.println("B 实现了Operation2");
    }

    @Override
    public void opearation3() {
        System.out.println("B 是实现了Operation3");

    }
}

package com.ctgu.Date6_4;

public class D implements Interface1,Interface3 {


    @Override
    public void operation1() {
        System.out.println("D 实现了Operation1");
    }

    @Override
    public void operation4() {
        System.out.println("D 实现了Operation4");
    }

    @Override
    public void operation5() {
        System.out.println("D 实现了Operation5");
    }
}

```
