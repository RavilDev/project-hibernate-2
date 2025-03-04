package org.example.safargulov.projecthibernate2.config;

import liquibase.Scope;
import liquibase.command.CommandScope;
import liquibase.resource.ClassLoaderResourceAccessor;

public class DataBaseConfig {
    public void init() {
        try {
            Scope.child(Scope.Attr.resourceAccessor, new ClassLoaderResourceAccessor(), () -> {
                CommandScope update = new CommandScope("update");

                update.addArgumentValue("changelogFile", "migrations/db.changelog.xml");
                update.addArgumentValue("url", "jdbc:mysql://localhost:3306/movie");
                update.addArgumentValue("username", "user");
                update.addArgumentValue("password", "user");
                update.execute();
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
