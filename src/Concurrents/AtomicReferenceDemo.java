package Concurrents;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Author:Young
 * Date:2020/10/19-23:01
 */
class User{
    String userName;
    int age;

    public User(String userName,int age){
        this.userName = userName;
        this.age = age;
    }
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User z3 = new User("z3",22);
        User li4 = new User("li4",22);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3,li4) + "\t" + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3,li4) + "\t" + atomicReference.get().toString());
    }
}
