package com.example.java;

import com.example.java.TestBase;
import com.example.java.models.Card;
import com.example.java.models.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.java.conditions.Conditions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.condition.JRE.*;
import static org.junit.jupiter.api.condition.OS.*;



public class CardsTests extends TestBase {


    @Test
    void addCardToUser(){
        Card card = new Card().setLongNum("4444 4444 4444 4444")
                .setExpires("19/02")
                .setCcv("123")
                .setUserID(testUser.id);
        userApiService.addCard(card)
                .shouldHave(statusCode(200))
                .shouldHave(contentType("application/json; charset=utf-8"))
                .shouldHave(body("id", is(notNullValue())));
    }


    @Test
    @Disabled
    void getAllCards(){
        List<Card> cards = userApiService.getCards()
                .shouldHave(statusCode(200))
                .shouldHave(contentType("application/json; charset=utf-8"))
                .shouldHave(body("_embedded.card.size()", is(1)))
                .response.extract().body().jsonPath().get("_embedded.card");
        assertTrue(cards.size() > 0);
        assertEquals(1, cards.size());
        assertEquals("5429804235432", cards.get(0).longNum);
        assertEquals("04/16", cards.get(0).expires);
        assertEquals("432", cards.get(0).ccv);
    }


    @Test
    @Disabled
    void getAllCardsWithGsonMapping(){
        Card card = gson.fromJson(userApiService.getCards()
                .shouldHave(statusCode(200))
                .shouldHave(contentType("text/plain; charset=utf-8"))
                .response.extract().body().asString(), Card.class);
        assertEquals("5429804235432", card.longNum);
        assertEquals("04/16", card.expires);
        assertEquals("432", card.ccv);
    }

}
