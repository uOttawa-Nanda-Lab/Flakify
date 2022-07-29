/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.as.test.integration.deployment.resourcelisting;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.as.test.integration.deployment.classloading.war.WebInfLibClass;
import org.jboss.as.test.shared.ResourceListingUtils;
import org.jboss.logging.Logger;
import org.jboss.modules.ModuleClassLoader;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(Arquillian.class)
public class WarResourceListingTestCase {

    private static final Logger log = Logger.getLogger(WarResourceListingTestCase.class);
    private static final String jarLibName = "innerJarLibrary.jar";


    @Test() public void testDirectResourceRetrieval(){log.trace("Test accessing resources using getResource method");ModuleClassLoader classLoader=(ModuleClassLoader)getClass().getClassLoader();URL manifestResource=classLoader.getResource("META-INF/example.txt");assertNotNull("Resource in META-INF should be accessible",manifestResource);URL nestedManifestResource=classLoader.getResource("META-INF/properties/nested.properties");assertNotNull("Nested resource should be also accessible",nestedManifestResource);URL nonManifestResource=classLoader.getResource("example2.txt");assertNull("Resource in the root of WAR shouldn't be accessible",nonManifestResource);}

    /**
     * Based on provided parameters it filters which resources should be available and which not and tests if the retrieved resources matches this list
     * @param recursive also a nested/recursive resources are counted
     * @param rootDir represents the filtering by root directory (only resources in the specified root dir are taken into account
     */
    private void doTestResourceRetrieval(boolean recursive, String rootDir) {
        ModuleClassLoader classLoader = (ModuleClassLoader) getClass().getClassLoader();

        List<String> resourcesInDeployment = getActualResourcesInWar(recursive, rootDir);

        List<String> foundResources = ResourceListingUtils.listResources(classLoader, rootDir, recursive);

        Collections.sort(foundResources);

        log.trace("List of expected resources:");
        for (String expectedResource : resourcesInDeployment) {
            log.trace(expectedResource);
        }
        log.trace("List of found resources: ");
        for (String foundResource : foundResources) {
            log.trace(foundResource);
        }

        Assert.assertArrayEquals("Not all resources from WAR archive are correctly listed", resourcesInDeployment.toArray(), foundResources.toArray());
    }

}
