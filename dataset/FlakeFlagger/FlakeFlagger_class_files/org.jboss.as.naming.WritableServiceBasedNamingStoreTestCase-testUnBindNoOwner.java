/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
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

package org.jboss.as.naming;

import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.naming.CompositeName;
import javax.naming.Name;
import javax.naming.NameNotFoundException;

import org.wildfly.naming.java.permission.JndiPermission;
import org.jboss.as.naming.deployment.ContextNames;
import org.jboss.as.naming.deployment.JndiNamingDependencyProcessor;
import org.jboss.as.naming.deployment.RuntimeBindReleaseService;
import org.jboss.as.naming.service.NamingStoreService;
import org.jboss.msc.service.LifecycleEvent;
import org.jboss.msc.service.LifecycleListener;
import org.jboss.msc.service.ServiceContainer;
import org.jboss.msc.service.ServiceController;
import org.jboss.msc.service.ServiceName;

import static org.jboss.as.naming.SecurityHelper.testActionWithPermission;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author John Bailey
 * @author Eduardo Martins
 */
public class WritableServiceBasedNamingStoreTestCase {
    private ServiceContainer container;
    private WritableServiceBasedNamingStore store;
    private static final ServiceName OWNER_FOO = ServiceName.of("Foo");
    private static final ServiceName OWNER_BAR = ServiceName.of("Bar");

    @Before
    public void setup() throws Exception {
        container = ServiceContainer.Factory.create();
        installOwnerService(OWNER_FOO);
        installOwnerService(OWNER_BAR);
        final CountDownLatch latch2 = new CountDownLatch(1);
        final NamingStoreService namingStoreService = new NamingStoreService();
        container.addService(ContextNames.JAVA_CONTEXT_SERVICE_NAME, namingStoreService)
                .setInitialMode(ServiceController.Mode.ACTIVE)
                .addListener(new LifecycleListener() {
                    public void handleEvent(ServiceController<?> controller, LifecycleEvent event) {
                        switch (event) {
                            case UP: {
                                latch2.countDown();
                                break;
                            }
                            case FAILED: {
                                latch2.countDown();
                                fail("Did not install store service - " + controller.getStartException().getMessage());
                                break;
                            }
                            default:
                                break;
                        }
                    }
                })
                .install();
        latch2.await(10, TimeUnit.SECONDS);
        store = (WritableServiceBasedNamingStore) namingStoreService.getValue();
    }

    private void installOwnerService(ServiceName owner) throws InterruptedException {
        final CountDownLatch latch1 = new CountDownLatch(1);
        container.addService(JndiNamingDependencyProcessor.serviceName(owner), new RuntimeBindReleaseService())
                .setInitialMode(ServiceController.Mode.ACTIVE)
                .addListener(new LifecycleListener() {
                    public void handleEvent(ServiceController<?> controller, LifecycleEvent event) {
                        switch (event) {
                            case UP: {
                                latch1.countDown();
                                break;
                            }
                            case FAILED: {
                                latch1.countDown();
                                fail("Did not install store service - " + controller.getStartException().getMessage());
                                break;
                            }
                            default:
                                break;
                        }
                    }
                })
                .install();
        latch1.await(10, TimeUnit.SECONDS);
    }

    @Test public void testUnBindNoOwner() throws Exception{try {store.unbind(new CompositeName("test"));fail("Should have failed with a read-only context exception");} catch (UnsupportedOperationException expected){}}
}
