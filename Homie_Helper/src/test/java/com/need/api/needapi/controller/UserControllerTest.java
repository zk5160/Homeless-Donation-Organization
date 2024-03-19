package com.need.api.needapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import com.need.api.needapi.persistence.UserDAO;
import com.need.api.needapi.model.FundingBasket;
import com.need.api.needapi.model.Need;
import com.need.api.needapi.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test the User Controller class
 * 
 * @author SWEN Faculty
 */
@Tag("Controller-tier")
public class UserControllerTest {
    private UserController UserController;
    private UserDAO mockUserDAO;

    /**
     * Before each test, create a new NeedController object and inject
     * a mock Need DAO
     */
    @BeforeEach
    public void setupNeedController() {
        mockUserDAO = mock(UserDAO.class);
        UserController = new UserController(mockUserDAO);
    }

    @Test
    public void testGetNeed() throws IOException {  // getNeed may throw IOException
        // Setup
         ArrayList<FundingBasket> array = new ArrayList<FundingBasket>();
        array.add(new FundingBasket(72,"T-shirt", 10.99f, 3, "shirt"));
        User user = new User(0, "Jade", array);
        // When the same id is passed in, our mock Need DAO will return the Need object
        when(mockUserDAO.getUser(user.getId())).thenReturn(user);

        // Invoke
        ResponseEntity<User> response = UserController.getUser(user.getId());

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(user,response.getBody());
    }

    @Test
    public void testGetNeedNotFound() throws Exception { // createHero may throw IOException
        // Setup
        int id = 9;
        // When the same id is passed in, our mock User DAO will return the User object
        when(mockUserDAO.getUser(id)).thenReturn(null);

        // Invoke
        ResponseEntity<User> response = UserController.getUser(id);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testGetNeedHandleException() throws Exception { // createHero may throw IOException
        // Setup
        int Id = 99;
        // When getHero is called on the Mock User DAO, throw an IOException
        doThrow(new IOException()).when(mockUserDAO).getUser(Id);

        // Invoke
        ResponseEntity<User> response = UserController.getUser(Id);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testCreateUser() throws IOException {  // createHero may throw IOException
        // Setup
        ArrayList<FundingBasket> array = new ArrayList<FundingBasket>();
        array.add(new FundingBasket(72,"T-shirt", 10.99f, 3, "shirt"));
        User user = new User(0, "Jade", array);
        // creation and save
        when(mockUserDAO.createUser(user)).thenReturn(user);

        // Invoke
        ResponseEntity<User> response = UserController.createUser(user);

        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(user,response.getBody());
    }

    @Test
    public void testCreateUserFailed() throws IOException {  // createHero may throw IOException
        // Setup
        // Setup
        ArrayList<FundingBasket> array = new ArrayList<FundingBasket>();
        array.add(new FundingBasket(72,"T-shirt", 10.99f, 3, "shirt"));
        User user = new User(0, "Jade", array);
        // creation and save
        when(mockUserDAO.createUser(user)).thenReturn(null);

        // Invoke
        ResponseEntity<User> response = UserController.createUser(user);

        // Analyze
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testCreateUserHandleException() throws IOException {  // createHero may throw IOException
        // Setup
        ArrayList<FundingBasket> array = new ArrayList<FundingBasket>();
        array.add(new FundingBasket(72,"T-shirt", 10.99f, 3, "shirt"));
        User user = new User(0, "Jade", array);
        // When createHero is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockUserDAO).createUser(user);

        // Invoke
        ResponseEntity<User> response = UserController.createUser(user);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testUpdateUser() throws IOException { // updateHero may throw IOException
        // Setup
        ArrayList<FundingBasket> array = new ArrayList<FundingBasket>();
        array.add(new FundingBasket(72,"T-shirt", 10.99f, 3, "shirt"));
        User user = new User(0, "Jade", array);
        // update and save
        when(mockUserDAO.updateUser(user)).thenReturn(user);
        ResponseEntity<User> response = UserController.updateUser(user);
        user.setName("Zara");

        // Invoke
        response = UserController.updateUser(user);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(user,response.getBody());
    }

    @Test
    public void testUpdateNeedFailed() throws IOException { // updateHero may throw IOException
        // Setup
        ArrayList<FundingBasket> array = new ArrayList<FundingBasket>();
        array.add(new FundingBasket(72,"T-shirt", 10.99f, 3, "shirt"));
        User user = new User(0, "Jade", array);
        // update and save
        when(mockUserDAO.updateUser(user)).thenReturn(null);

        // Invoke
        ResponseEntity<User> response = UserController.updateUser(user);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testUpdateUserHandleException() throws IOException { // updateHero may throw IOException
        // Setup
        ArrayList<FundingBasket> array = new ArrayList<FundingBasket>();
        array.add(new FundingBasket(72,"T-shirt", 10.99f, 3, "shirt"));
        User user = new User(0, "Jade", array);
        doThrow(new IOException()).when(mockUserDAO).updateUser(user);

        // Invoke
        ResponseEntity<User> response = UserController.updateUser(user);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testGetUsers() throws IOException { // getHeroes may throw IOException
        // Setup
        User[] users = new User[2];
        // Setup
        ArrayList<FundingBasket> array = new ArrayList<FundingBasket>();
        array.add(new FundingBasket(72,"T-shirt", 10.99f, 3, "shirt"));
        users[0] = new User(0, "Jade", array);
        
        ArrayList<FundingBasket> array1 = new ArrayList<FundingBasket>();
        array.add(new FundingBasket(13,"candy", 9.5f, 3, "food"));
        users[1] = new User(1, "Zara", array1);
        // When getHeroes is called return the users created above
        when(mockUserDAO.getUser()).thenReturn(users);

        // Invoke
        ResponseEntity<User[]> response = UserController.getUsers();

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(users,response.getBody());
    }

    @Test
    public void testGetUsersHandleException() throws IOException { // getUsers may throw IOException
        // Setup
        // When getHeroes is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockUserDAO).getUser();

        // Invoke
        ResponseEntity<User[]> response = UserController.getUsers();

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testSearchUsers() throws IOException { // findHeroes may throw IOException
        // Setup
        String searchString = "a";
        User[] users = new User[2];
        // Setup
        ArrayList<FundingBasket> array = new ArrayList<FundingBasket>();
        array.add(new FundingBasket(72,"T-shirt", 10.99f, 3, "shirt"));
        users[0] = new User(0, "Jade", array);
        
        ArrayList<FundingBasket> array1 = new ArrayList<FundingBasket>();
        array.add(new FundingBasket(13,"candy", 9.5f, 3, "food"));
        users[1] = new User(1, "Zara", array1);
        
        // When findUsers is called with the search string, return the two
        /// users above
        when(mockUserDAO.getUser()).thenReturn(users);
        // Invoke
        ResponseEntity<User[]> response = UserController.searchUsers(searchString);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testSearchUsersHandleException() throws IOException { // findHeroes may throw IOException
        // Setup
        String searchString = "an";
        // When createHero is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockUserDAO).getUser();

        // Invoke
        ResponseEntity<User[]> response = UserController.searchUsers(searchString);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testDeleteUser() throws IOException { // deleteHero may throw IOException
        // Setup
        int Id = 99;
        // when deleteHero is called return true, simulating successful deletion
        when(mockUserDAO.deleteUser(Id)).thenReturn(true);

        // Invoke
        ResponseEntity<User> response = UserController.deleteUser(Id);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testDeleteUserNotFound() throws IOException { // deleteHero may throw IOException
        // Setup
        int Id = 99;
        // when deleteHero is called return false, simulating failed deletion
        when(mockUserDAO.deleteUser(Id)).thenReturn(false);

        // Invoke
        ResponseEntity<User> response = UserController.deleteUser(Id);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testDeleteUserHandleException() throws IOException { // deleteHero may throw IOException
        // Setup
        int Id = 99;
        // When deleteHero is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockUserDAO).deleteUser(Id);

        // Invoke
        ResponseEntity<User> response = UserController.deleteUser(Id);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}
