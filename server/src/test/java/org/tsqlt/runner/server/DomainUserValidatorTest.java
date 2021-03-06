package org.tsqlt.runner.server;

import jetbrains.buildServer.serverSide.InvalidProperty;
import org.testng.annotations.Test;
import org.tsqlt.runner.common.PropertyNames;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

public class DomainUserValidatorTest {
    private final Validator sut = new DomainUserValidator();
    @Test
    public void testItIsInvalidWhenEmpty(){
        Map<String, String> properties = new HashMap<String, String>();
        InvalidProperty error = sut.hasErrors(properties);

        assertNotNull(error);
        assertEquals(error.getPropertyName(), PropertyNames.USER_DOMAIN);
    }

    @Test
    public void testItIsInvalidIfMoreThanOneDomain(){
        Map<String, String> properties = new HashMap<String, String>(){{
            put(PropertyNames.USER_DOMAIN, "domain\\user\\invalid");
        }};
        InvalidProperty error = sut.hasErrors(properties);

        assertNotNull(error);
        assertEquals(error.getPropertyName(), PropertyNames.USER_DOMAIN);
    }

    @Test
    public void testItIsValidWithUserAndDomain(){
        Map<String, String> properties = new HashMap<String, String>(){{
            put(PropertyNames.USER_DOMAIN, "domain\\user");
        }};
        InvalidProperty error = sut.hasErrors(properties);

        assertNull(error);
    }

    public void testItIsValidWithOnlyUser(){
        Map<String, String> properties = new HashMap<String, String>(){{
            put(PropertyNames.USER_DOMAIN, "user");
        }};
        InvalidProperty error = sut.hasErrors(properties);

        assertNull(error);
    }
}
