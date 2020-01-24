package com.example.equipo2_crudapp_android.client.interfaces;

import java.util.Set;

import equipo2_crudapp_classes.classes.User;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserInterface {

    /**
     * Creates a new user in the database
     * @param user User to create
     */
    @POST
    public void createUser(User user);

    /**
     * Updates an existing user in the database
     * @param user User that is going to be modified with the new values
     */
    @PUT
    public void modifyUser(User user);

    /**
     * Deletes an specified user
     * @param userId Id of the user to delete
     */
    @DELETE("{id}")
    public void deleteUser(@Path("id") Integer userId);

    /**
     * Search for an specified user in the database
     * @param userId Id of the user to search
     * @return The user found
     */
    @GET("{id}")
    public User findUser(@Path("id") Integer userId);

    /**
     * Method that searches for a user with the specified email
     *
     * @param email Email of the user to find
     * @return The user found
     */
    @GET
    public User findUserByEmail(String email);

    /**
     * Finds and returns a list containing all the users from the database.
     *
     * @return List of type User with all the users found.
     */
    @GET
    public Set<User> findAllUsers();

    /**
     * Method to check the credentials of a user.
     *
     * @param login Login of the user.
     * @param password Password of the user.
     * @return The user, if found.
     */
    @GET
    public User checkUserPassword(String login, String password);

}
