package com.ebbi.EbbiCards.repositories;

public class CardRepository extends AbstractHibernateRepository<Card> {

    public CardRepository() {
        setClazz(Card.class);
    }
}
