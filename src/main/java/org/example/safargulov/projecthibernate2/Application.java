package org.example.safargulov.projecthibernate2;

import org.example.safargulov.projecthibernate2.config.DataBaseConfig;

public class Application {
    public static void main(String[] args) {
        new DataBaseConfig().init();
    }
}
