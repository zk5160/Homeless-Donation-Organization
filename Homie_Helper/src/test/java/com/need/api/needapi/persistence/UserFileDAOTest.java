package com.need.api.needapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.need.api.needapi.model.FundingBasket;
import com.need.api.needapi.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Test the User File DAO class
 * 
 * @author SWEN Faculty
 */
@Tag("Persistence-tier")
public class UserFileDAOTest {
    UserFileDAO userFileDAO;
    User[] testUsers;
    ObjectMapper mockObjectMapper;

    /**
     * Before each test, we will create and inject a Mock Object Mapper to
     * isolate the tests from the underlying file
     * @throws IOException
     */
    @BeforeEach
    public void setupNeedFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testUsers = new User[3];
        ArrayList<FundingBasket> array = new ArrayList<FundingBasket>();
        array.add(new FundingBasket(72,"T-shirt", 10.99f, 3, "shirt"));
        testUsers[0] = new User(0, "Jade", array);
        
        ArrayList<FundingBasket> array1 = new ArrayList<FundingBasket>();
        array1.add(new FundingBasket(13,"candy", 9.5f, 3, "food"));
        testUsers[1] = new User(1, "Ellie", array1);

        ArrayList<FundingBasket> array2= new ArrayList<FundingBasket>();
        array2.add(new FundingBasket(10,"milk", 3.5f, 1, "food"));
        testUsers[2] = new User(2, "Zara", array2);

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the need array above
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"),User[].class))
                .thenReturn(testUsers);
        userFileDAO = new UserFileDAO("doesnt_matter.txt",mockObjectMapper);
    }

    @Test
    public void testGetUsers() {
        // Invoke
        User[] users = userFileDAO.getUser();

        // Analyze
        assertEquals(users.length,testUsers.length);
        for (int i = 0; i < testUsers.length;++i)
            assertEquals(users[i],testUsers[i]);
    }

    @Test
    public void testFindUsers() {
        // Invoke
        User[] users = userFileDAO.findUser("a");

        // Analyze
        assertEquals(users.length,2);
        assertEquals(users[0],testUsers[0]);
        assertEquals(users[1],testUsers[2]);
    }

    @Test
    public void testGetUser() {
        // Invoke
        User user = userFileDAO.getUser(0);

        // Analzye
        assertEquals(user,testUsers[0]);
    }

    @Test
    public void testDeleteUser() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> userFileDAO.deleteUser(0),
                            "Unexpected exception thrown");

        // Analzye
        assertEquals(result,true);
        // We check the internal tree map size against the length
        // of the test needs array - 1 (because of the delete)
        // Because needs attribute of NeedFileDAO is package private
        // we can access it directly
        assertEquals(userFileDAO.users.size(),testUsers.length-1);
    }

    @Test
    public void testCreateUser() {
        //setup
        ArrayList<FundingBasket> array3 = new ArrayList<FundingBasket>();
        array3.add(new FundingBasket(13,"candy", 9.5f, 3, "food"));
        User user = new User(3, "Ask", array3);

        // Invoke
        User result = assertDoesNotThrow(() -> userFileDAO.createUser(user),
                                "Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        User actual = userFileDAO.getUser(user.getId());
        
        // temporarily commented out, actual.getProperty() returns null
         assertEquals(actual.getId(),user.getId());
         assertEquals(actual.getName(),user.getName());
    }

    @Test
    public void testUpdateUser() {
        // Setup
        ArrayList<FundingBasket> array3 = new ArrayList<FundingBasket>();
        array3.add(new FundingBasket(13,"candy", 9.5f, 3, "food"));
        User user = new User(2, "Zara", array3);

        // Invoke
        User result = assertDoesNotThrow(() -> userFileDAO.updateUser(user),
                                "Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        User actual = userFileDAO.getUser(user.getId());
        assertEquals(actual,user);
    }

    @Test
    public void testSaveException() throws IOException{
        doThrow(new IOException())
            .when(mockObjectMapper)
                .writeValue(any(File.class),any(User[].class));

        ArrayList<FundingBasket> array3 = new ArrayList<FundingBasket>();
        array3.add(new FundingBasket(13,"candy", 9.5f, 3, "food"));
        User user = new User(2, "Zara", array3);

        assertThrows(IOException.class,
                        () -> userFileDAO.createUser(user),
                        "IOException not thrown");
    }

    @Test
    public void testGetUserNotFound() {
        // Invoke
        User user = userFileDAO.getUser(4);

        // Analyze
        assertEquals(user,null);
    }

    @Test
    public void testDeleteUserNotFound() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> userFileDAO.deleteUser(4),
                                                "Unexpected exception thrown");

        // Analyze
        assertEquals(result,false);
        assertEquals(userFileDAO.users.size(),testUsers.length); // note: keyword public added to NeedFileDAO Map needs for accessibility
    }

    @Test
    public void testUpdateUserNotFound() {
        // Setup
        ArrayList<FundingBasket> array3 = new ArrayList<FundingBasket>();
        array3.add(new FundingBasket(13,"candy", 9.5f, 3, "food"));
        User user = new User(4, "Ask", array3);

        // Invoke
        User result = assertDoesNotThrow(() -> userFileDAO.updateUser(user),
                                                "Unexpected exception thrown");

        //Object result = null;

        // Analyze
        assertNull(result);
    }

    @Test
    public void testConstructorException() throws IOException {
        // Setup
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
        // We want to simulate with a Mock Object Mapper that an
        // exception was raised during JSON object deseerialization
        // into Java objects
        // When the Mock Object Mapper readValue method is called
        // from the NeedFileDAO load method, an IOException is
        // raised
        doThrow(new IOException())
            .when(mockObjectMapper)
                .readValue(new File("doesnt_matter.txt"),User[].class);

        // Invoke & Analyze
        assertThrows(IOException.class,
                        () -> new UserFileDAO("doesnt_matter.txt",mockObjectMapper),
                        "IOException not thrown");
    }

}
