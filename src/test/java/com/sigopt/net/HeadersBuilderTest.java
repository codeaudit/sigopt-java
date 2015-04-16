package com.sigopt.net;

import com.sigopt.Sigopt;
import com.sigopt.exception.AuthenticationException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HeadersBuilderTest {
    @BeforeClass
    public static void setUp() {
    }

    @Before
    public void setUpMockData() {
    }

    @Test
    public void buildWithExistingHeaders() throws Exception {
        Map<String, String> existing = new HashMap<String, String>();
        existing.put("dog", "dog-value");
        Map<String, String> actual = HeadersBuilder.build(existing, "actual-api-key");

        assertTrue(actual.containsKey("Authorization"));
        assertTrue(actual.containsKey("User-Agent"));
        assertEquals("dog-value", actual.get("dog"));
    }

    @Test
    public void buildWithNullHeaders() throws Exception {
        Map<String, String> actual = HeadersBuilder.build(null, "actual-api-key");

        assertTrue(actual.containsKey("Authorization"));
        assertTrue(actual.containsKey("User-Agent"));
    }

    @Test
    public void buildWithApiKey() throws Exception {
        Map<String, String> actual = HeadersBuilder.build(new HashMap<String, String>(), "actual-api-key");
        assertTrue(actual.containsKey("Authorization"));
    }

    @Test
    public void buildWithKeys() throws Exception {
        Map<String, String> actual = HeadersBuilder.build(new HashMap<String, String>(), "actual-api-key", "AuthToken");

        assertEquals(actual.get("AuthToken"), "actual-api-key");
    }

    @Test
    public void defaultHeaders() throws Exception {
        Map<String, String> actual = HeadersBuilder.defaultHeaders();
        assertTrue("Default headers should contain the API Version.", actual.get("User-Agent").contains("Sigopt/" + Sigopt.apiVersion));
        assertTrue("Default headers should contain the Library Version.", actual.get("User-Agent").contains("JavaBindings/" + Sigopt.VERSION));
    }

    @Test
    public void customAuthHeader() throws Exception {
        Map<String, String> actual = HeadersBuilder.customAuthHeader("AuthToken", "actual-auth-value");
        assertEquals("actual-auth-value", actual.get("AuthToken"));
    }

    @Test
    public void basicAuthHeaderWithNull() throws Exception {
        try {
            HeadersBuilder.basicAuthHeader(null);
            assertTrue("Authentication exception was never thrown", false);
        } catch (AuthenticationException e) {
            assertTrue("AuthenticationException should contain the docs url.", e.getMessage().contains(Sigopt.DOCS_URL));
        }
    }

    @Test
    public void basicAuthHeaderWithKey() throws Exception {
        Map<String, String> actual = HeadersBuilder.basicAuthHeader("api-key");
        String decoded = new String(Base64.getDecoder().decode(actual.get("Authorization")));

        assertEquals("api-key:", decoded);
    }




}
