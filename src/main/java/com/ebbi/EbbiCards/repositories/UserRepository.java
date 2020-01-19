package com.ebbi.EbbiCards.repositories;

public class UserRepository extends AbstractHibernateRepository<User> {

    public UserRepository() {
        setClazz(User.class);
    }
}
