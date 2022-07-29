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

    @Test public void testWildcardFilterSuffixGlob() throws Exception{VirtualFileFilter filter=Filters.wildcard("/this/is*");VirtualFile good=VFS.getChild("/this/is/a/test.txt");VirtualFile bad=VFS.getChild("/that/is/a/test.txt");VirtualFile wingood=VFS.getChild("/C:/this/is/a/test.txt");VirtualFile winbad=VFS.getChild("/C:/that/is/a/test.txt");assertTrue(filter.accepts(good));assertFalse(filter.accepts(bad));assertTrue(filter.accepts(wingood));assertFalse(filter.accepts(winbad));}

    private void safeClose(JdrZipFile zf) {
        try {
            zf.close();
        } catch (Exception ignored) { }
    }

}
