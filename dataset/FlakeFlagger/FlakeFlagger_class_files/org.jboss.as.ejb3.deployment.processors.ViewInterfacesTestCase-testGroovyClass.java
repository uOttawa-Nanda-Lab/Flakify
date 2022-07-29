package org.jboss.as.ejb3.deployment.processors;

import groovy.lang.MetaClass;
import java.util.Set;
import javax.jms.MessageListener;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests {@link ViewInterfaces}
 *
 * @author Filipe Ferraz
 * @version $Revision: $
 */
public class ViewInterfacesTestCase {

    /**
	 * Tests that the   {@link  ViewInterfaces#getPotentialViewInterfaces(Class<?>)}  returns the correctimplementation class for groovy class implementing MessageListener interface.
	 */@Test public void testGroovyClass(){Set groovyClasses=ViewInterfaces.getPotentialViewInterfaces(TestGroovyMessageListener.class);Assert.assertEquals("One object epected in Groovy class",1,groovyClasses.size());Assert.assertEquals("Expected interface in Groovy class",MessageListener.class,groovyClasses.iterator().next());}

    private abstract class TestJavaMessageListener implements MessageListener {
    }

    private abstract class TestGroovyMessageListener implements MessageListener, MetaClass {
    }

}
