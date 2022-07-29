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
package org.jboss.as.jdr;

import org.jboss.as.jdr.commands.JdrEnvironment;
import org.jboss.as.jdr.util.JdrZipFile;
import org.jboss.as.jdr.util.PatternSanitizer;
import org.jboss.as.jdr.util.XMLSanitizer;
import org.jboss.as.jdr.vfs.Filters;
import org.jboss.vfs.VFS;
import org.jboss.vfs.VirtualFile;
import org.jboss.vfs.VirtualFileFilter;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import static org.junit.Assert.*;

public class JdrTestCase {

    @Test public void testXMLSanitizer() throws Exception{String xml="<test><password>foobar</password></test>";InputStream is=new ByteArrayInputStream(xml.getBytes());XMLSanitizer s=new XMLSanitizer("//password",Filters.TRUE);InputStream res=s.sanitize(is);byte[] buf=new byte[res.available()];res.read(buf);assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><test><password/></test>",new String(buf));}

    private void safeClose(JdrZipFile zf) {
        try {
            zf.close();
        } catch (Exception ignored) { }
    }

}
