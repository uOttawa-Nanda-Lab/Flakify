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

import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.naming.Binding;
import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameClassPair;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import org.jboss.msc.service.Service;
import org.jboss.msc.service.ServiceContainer;
import org.jboss.msc.service.ServiceName;
import org.jboss.msc.service.StartContext;
import org.jboss.msc.service.StartException;
import org.jboss.msc.service.StopContext;
import org.jboss.msc.value.Values;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author John Bailey
 */
public class ServiceBasedNamingStoreTestCase {

    private ServiceContainer container;
    private ServiceBasedNamingStore store;

    @Before
    public void setupServiceContainer() {
        container = ServiceContainer.Factory.create();
        store = new ServiceBasedNamingStore(container, ServiceName.JBOSS);
    }

    @Test
    public void testList() throws Exception {
        final Object value = new Object();
        bindObject(ServiceName.JBOSS.append("TestBean"), value);
        bindObject(ServiceName.JBOSS.append("foo", "TestBean"), value);
        bindObject(ServiceName.JBOSS.append("foo", "bar", "TestBean"), value);
        bindObject(ServiceName.JBOSS.append("foo", "bar", "baz", "TestBean"), value);

        store.add(ServiceName.JBOSS.append("foos", "bar"));
        store.add(ServiceName.JBOSS.append("fo", "bar"));
        store.add(ServiceName.JBOSS.append("foo", "ba", "baz"));
        store.add(ServiceName.JBOSS.append("foo", "bart", "baz"));
        store.add(ServiceName.JBOSS.append("foo", "bar", "ba"));
        store.add(ServiceName.JBOSS.append("foo", "bar", "bazt"));
        store.add(ServiceName.JBOSS.append("foo", "bar", "art"));
        store.add(ServiceName.JBOSS.append("other", "one"));

        List<NameClassPair> list = store.list(new CompositeName(""));
        assertEquals(5, list.size());
        assertContains(list, "TestBean", Object.class);
        assertContains(list, "foo", Context.class);
        assertContains(list, "fo", Context.class);
        assertContains(list, "foos", Context.class);
        assertContains(list, "other", Context.class);


        list = store.list(new CompositeName("foo"));
        assertEquals(4, list.size());
        assertContains(list, "TestBean", Object.class);
        assertContains(list, "ba", Context.class);
        assertContains(list, "bart", Context.class);
        assertContains(list, "bar", Context.class);
    }

    private void assertContains(final List<? extends NameClassPair> list, String name, Class<?> type) {
        for (NameClassPair value : list) {
            if (value instanceof Binding) {
                assertNotNull(Binding.class.cast(value).getObject());
            }
            if (value.getName().equals(name) && value.getClassName().equals(type.getName())) {
                return;
            }
        }
        fail("Child [" + name + "] not found in [" + list + "]");
    }

    private void bindObject(final ServiceName serviceName, final Object value) throws Exception {
        final CountDownLatch latch = new CountDownLatch(1);
        container.addService(serviceName, new Service<ManagedReferenceFactory>() {
            public void start(StartContext context) throws StartException {
                store.add(serviceName);
                latch.countDown();
            }

            public void stop(StopContext context) {
            }

            public ManagedReferenceFactory getValue() throws IllegalStateException, IllegalArgumentException {
                return new ValueManagedReferenceFactory(Values.immediateValue(value));
            }
        }).install();
        latch.await();
    }
}
