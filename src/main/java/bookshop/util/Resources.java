package bookshop.util;

import org.codehaus.jackson.map.ObjectMapper;

import javax.enterprise.inject.Produces;

public class Resources {

    @Produces
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
