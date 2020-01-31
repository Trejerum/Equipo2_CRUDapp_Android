package com.example.equipo2_crudapp_android.client.interfaces;

import java.util.Set;

import equipo2_crudapp_classes.classes.Shop;
import retrofit2.Call;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ShopInterface {


    /**
     * Creates a new shop in the database
     * @param shop Shop to create
     */
    @POST("shop/createShop")
    public Call<SimpleXmlConverterFactory> createShop(@Body Shop shop);

    /**
     * Updates an existing user in the database
     * @param shop Shop that is going to be modified with the new values
     */
    @PUT
    public void modifyShop(Shop shop);

    /**
     * Deletes an specified shop
     * @param shop Shop to delete
     */
    @DELETE
    public void deleteShop(Shop shop);

    /**
     * Search for an specified shop in the database
     * @param shopId Id of the shop to search
     * @return The shop found
     */
    @GET
    public Shop findShop(Integer shopId);

    /**
     * Finds and returns a list containing all the shops from the database.
     *
     * @return List of type Shop with all the shops found.
     */
    @GET
    public Set<Shop> findAllShops();

}
