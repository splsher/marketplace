package org.example;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private static AtomicInteger autogenIdUser = new AtomicInteger();
    private int id;
    private String firstName;
    private String lastName;
    private double amountMoney;

    public User(int id, String firstName, String lastName, double amountMoney) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.amountMoney = amountMoney;
    }

    public User(String firstName, String lastName, double amountMoney) {

        this.id = autogenIdUser.getAndIncrement();

        if (firstName == null) {
            throw new IllegalArgumentException("FirstName can't be null");
        }

        if (lastName == null) {
            throw new IllegalArgumentException("LastName can't be null");
        }

        if (amountMoney <= 0) {
            throw new IllegalArgumentException("AmountMoney can't be 0 or minus");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.amountMoney = amountMoney;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(double amountMoney) {
        this.amountMoney = amountMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id && Double.compare(user.amountMoney, amountMoney) == 0 && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, id, amountMoney);
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", amountMoney=" + amountMoney +
                '}';
    }
}
