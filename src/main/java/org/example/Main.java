package org.example;

import javafx.util.Pair;

import java.util.*;

public class Main {
    private static List<User> users = new ArrayList<>();
    private static List<Product> products = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        addProducts();
        addUsers();
        input();
    }

    private static void addProduct() {
        System.out.println("Add Product: \n");
        System.out.println("Input name: \n");
        String name = scanner.next();
        System.out.println("Input price : \n");
        double price = scanner.nextDouble();
        Product product = new Product(name, price);
        products.add(product);
    }
    public static void addProducts() {
        System.out.println("Do you want to add new product? Please input 'Yes' or 'No' \n ");
        String choice = scanner.next();
        String action = "";
        if (choice.equalsIgnoreCase("yes")) {
            do {
                addProduct();

                System.out.println("Do you want to add one more product?");
                action = scanner.next();
            } while (action.equalsIgnoreCase("yes"));
        } else {
            System.out.println(products);
        }
    }

    private static void addUser() {
        System.out.println("Add user: \n");
        System.out.println("Input fist name: \n");
        String firstName = scanner.next();
        System.out.println("Input last name: \n");
        String lastName = scanner.next();
        System.out.println("Input amount of money : \n");
        double amountMoney = scanner.nextDouble();
        User user = new User(firstName, lastName, amountMoney);
        users.add(user);
    }
    public static void addUsers() {
        System.out.println("Do you want to add new user? Please input 'Yes' or 'No' \n ");
        String choice = scanner.next();
        String action = "";
        if (choice.equalsIgnoreCase("yes")) {
            do {
                addUser();

                System.out.println("Do you want to add one more user? (y - Yes / n - No)");
                action = scanner.next();
        } while (action.equals("y"));
        } else {
            System.out.println(users);
        }
    }

    public static void input() {
        System.out.println("Please choose options from 1 to 5: \n");
        int choice = scanner.nextInt();

        Map<Integer, Product> userProducts = new HashMap<>();
        Map<Integer, User> productByUsers = new HashMap<>();

        if (choice >= 1 && choice <= 5) {
            switch (choice) {
                case 1:
                    System.out.println("User : \n");
                    System.out.println(users);
                    break;
                case 2:
                    System.out.println("Products : \n");
                    System.out.println(products);
                    break;
                case 3:
                    System.out.println("Input Id of user who want to buy product: \n");
                    int userId = scanner.nextInt();
                    System.out.println("Input Id of product which user want to buy: \n");
                    int productId = scanner.nextInt();
                    Product product = products.stream()
                            .filter(x -> x.getId() == productId)
                            .findFirst()
                            .get();

                    User user = users.stream()
                            .filter(x -> x.getId() == userId)
                            .findFirst()
                            .get();
                    if (user.getAmountMoney() < product.getPrice()) {
                        throw new IllegalArgumentException("User doesn't have enough money to buy product");
                    } else {
                        System.out.println("User successfully bought the product");

                       user.setAmountMoney(user.getAmountMoney() - product.getPrice());

                        userProducts.put(user.getId(), product);
                        productByUsers.put(product.getId(), user);

                    }
                    break;
                case 4:
                    System.out.println("List of user products by user id \n");
                    System.out.println("Input Id of user: \n");
                    int usId = scanner.nextInt();
                    System.out.println(userProducts.get(usId));
                    break;
                case 5:
                    System.out.println("List of users that bought product by product id \n");
                    System.out.println("Input Id of product: \n");
                    int prodId = scanner.nextInt();
                    System.out.println(productByUsers.get(prodId));
                    break;
            }

        } else {
            throw new IllegalArgumentException("You should choose options from 1 to 5");
        }
    }
}