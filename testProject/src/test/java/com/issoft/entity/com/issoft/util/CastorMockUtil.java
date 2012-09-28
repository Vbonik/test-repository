package com.issoft.entity.com.issoft.util;

import com.issoft.entity.UserRole;
import org.apache.log4j.Logger;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.issoft.entity.User;
import org.xml.sax.InputSource;

/**
 * Allows to generate mock java objects of custom classes
 * Uses Castor framework
 */
public class CastorMockUtil {

    /**
     * Custom classes names
     */
    public enum MockEntity {
        /**
         * Name for {@link User} class
         */
        USER,
        /**
         * Name for {@link UserRole} class
         */
        USER_ROLE
    }

    /**
     * Log4j logger instance
     */
    private static final Logger LOGGER = Logger.getLogger(CastorMockUtil.class);
    /**
     * Castor mapping file path
     */
    private static final String CASTOR_MAPPING_FILE_PATH = "src/test/resources/castor/mapping.xml";
    /**
     * Map with paths to mock xml files
     */
    private static final Map<MockEntity, String> pathMap = new HashMap<MockEntity, String>();

    /**
     * Initialization of paths map
     */
    static {
        pathMap.put(MockEntity.USER, "src/test/resources/castor/mock/userMock.xml");
        pathMap.put(MockEntity.USER_ROLE, "src/test/resources/castor/mock/userRoleMock.xml");
    }

    /**
     * Defines which type of object to generate and calls specific method with appropriate parameters
     * @param mockEntity {@link MockEntity} to identify object class to generate
     * @return Generated object
     */
    public static Object createMock(MockEntity mockEntity) {
        if (mockEntity == null) {
            LOGGER.error("MockEntity parameter can't be null");
            throw new IllegalArgumentException();
        }
        switch (mockEntity) {
            case USER:
                return createCastorObject(pathMap.get(MockEntity.USER));
            case USER_ROLE:
                return createCastorObject(pathMap.get(MockEntity.USER_ROLE));
            default:
                LOGGER.error("Illegal parameter was provided");
                throw new IllegalArgumentException();
        }
    }

    /**
     *  Creates castor object using mapping configuration and mock xml file
     * @param path Path to mock xml file
     * @return Generated object from xml file
     */
    private static Object createCastorObject(String path) {
        Object resultObject = null;
        try {
            Mapping mapping = new Mapping();
            mapping.loadMapping(CASTOR_MAPPING_FILE_PATH);
            Unmarshaller unmarshaller = new Unmarshaller(mapping);
            resultObject = unmarshaller.unmarshal(new InputSource(new FileReader(path)));
        } catch (MarshalException exception) {
            LOGGER.error(exception);
        } catch (ValidationException exception) {
            LOGGER.error(exception);
        } catch (MappingException exception) {
            LOGGER.error(exception);
        } catch (IOException exception) {
            LOGGER.error(exception);
        }
        return resultObject;
    }
}
