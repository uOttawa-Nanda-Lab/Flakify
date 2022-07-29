package org.jboss.as.test.integration.web.handlestypes;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.servlet.ServletContainerInitializer;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Stuart Douglas
 */
@RunWith(Arquillian.class)
public class HandlesTypesEarTestCase {

    @Test public void testAnnotatedClass(){Class<?>[] expeccted={AnnotatedParent.class,AnnotatedChild.class};Assert.assertEquals(new HashSet<>(Arrays.asList(expeccted)),AnnotationServletContainerInitializer.HANDLES_TYPES);}
}
