/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat Inc., and individual contributors as indicated
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
package org.jboss.as.weld.discovery;

import java.io.IOException;
import java.lang.annotation.Target;
import java.lang.reflect.Modifier;

import javax.enterprise.inject.Vetoed;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.as.weld.discovery.InnerClasses.InnerInterface;
import org.jboss.as.weld.discovery.vetoed.Bravo;
import org.jboss.weld.resources.spi.ClassFileInfo;
import org.jboss.weld.resources.spi.ClassFileServices;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class WeldClassFileServicesTest {

    private static ClassFileInfo alpha;
    private static ClassFileInfo abstractAlpha;
    private static ClassFileInfo alphaImpl;
    private static ClassFileInfo innerInterface;
    private static ClassFileInfo bravo;
    private static ClassFileInfo charlie;

    @BeforeClass
    public static void init() throws IOException {
        ClassFileServices service = new WeldClassFileServices(IndexUtils.createIndex(Alpha.class, AlphaImpl.class, AbstractAlpha.class, InnerClasses.class,
                Bravo.class, "org/jboss/as/weld/discovery/vetoed/package-info.class", Inject.class, Named.class, Charlie.class), Thread.currentThread()
                .getContextClassLoader());
        alpha = service.getClassFileInfo(Alpha.class.getName());
        abstractAlpha = service.getClassFileInfo(AbstractAlpha.class.getName());
        alphaImpl = service.getClassFileInfo(AlphaImpl.class.getName());
        innerInterface = service.getClassFileInfo(InnerClasses.InnerInterface.class.getName());
        bravo = service.getClassFileInfo(Bravo.class.getName());
        charlie = service.getClassFileInfo(Charlie.class.getName());
    }

    @Test public void testIsAssignableFrom(){Assert.assertTrue(alpha.isAssignableFrom(AlphaImpl.class));Assert.assertTrue(abstractAlpha.isAssignableFrom(AlphaImpl.class));Assert.assertFalse(abstractAlpha.isAssignableFrom(Alpha.class));Assert.assertTrue(innerInterface.isAssignableFrom(Bravo.class));Assert.assertTrue(alphaImpl.isAssignableFrom(Bravo.class));}
}
