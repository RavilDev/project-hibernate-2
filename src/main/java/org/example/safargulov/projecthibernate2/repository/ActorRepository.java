package org.example.safargulov.projecthibernate2.repository;

import org.example.safargulov.projecthibernate2.entity.Actor;

public class ActorRepository extends AbstractRepository<Actor, Integer> {
    public ActorRepository() {
        super(Actor.class);
    }
}
