/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2010, Red Hat, Inc., and individual contributors
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

import org.junit.After;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

import javax.naming.Binding;
import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.NameClassPair;
import javax.naming.NameNotFoundException;
import javax.naming.Reference;
import javax.naming.spi.ResolveResult;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author John E. Bailey
 */
public class InMemoryNamingStoreTestCase {

    private final InMemoryNamingStore nameStore = new InMemoryNamingStore();

    @Test public void testListBindings() throws Exception{final Name name=new CompositeName("test");final Object object=new Object();nameStore.bind(name,object);final Name nameTwo=new CompositeName("testTwo");final Object objectTwo=new Object();nameStore.bind(nameTwo,objectTwo);final Name nameThree=new CompositeName("testThree");final Object objectThree=new Object();nameStore.bind(nameThree,objectThree);nameStore.bind(new CompositeName("testContext/test"),"test");final List<Binding> results=nameStore.listBindings(new CompositeName());assertEquals(4,results.size());final Set<String> expected=new HashSet<String>(Arrays.asList("test","testTwo","testThree","testContext"));for (Binding result:results){final String resultName=result.getName();if ("test".equals(resultName)){assertEquals(Object.class.getName(),result.getClassName());assertEquals(object,result.getObject());} else if ("testTwo".equals(resultName)){assertEquals(Object.class.getName(),result.getClassName());assertEquals(objectTwo,result.getObject());} else if ("testThree".equals(resultName)){assertEquals(Object.class.getName(),result.getClassName());assertEquals(objectThree,result.getObject());} else if ("testContext".equals(resultName)){assertEquals(Context.class.getName(),result.getClassName());} else {fail("Unknown result name: " + resultName);}expected.remove(resultName);}assertTrue("Not all expected results were returned",expected.isEmpty());}
}
