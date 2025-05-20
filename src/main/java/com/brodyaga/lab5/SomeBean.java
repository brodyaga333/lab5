package com.brodyaga.lab5;


import com.brodyaga.lab5.annotations.AutoInjectable;
import com.brodyaga.lab5.interfaces.SomeInterface;
import com.brodyaga.lab5.interfaces.SomeOtherInterface;


/**
 * Класс, поля которого будут автоматически заполняться внедряемыми зависимостями.
 */
public class SomeBean {
    @AutoInjectable
    private SomeInterface field1;

    @AutoInjectable
    private SomeOtherInterface field2;


    /**
     * Метод, вызывающий поведение внедрённых зависимостей.
     */
    public void foo() {
        field1.doSomething();
        field2.doSomeOther();
    }
}
