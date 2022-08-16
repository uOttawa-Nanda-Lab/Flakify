/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2015, Red Hat, Inc., and individual contributors
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

package org.wildfly.clustering.web.session;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Test;
import org.wildfly.clustering.marshalling.spi.Serializer;
import org.wildfly.clustering.web.IdentifierSerializer;

import io.undertow.server.session.SecureRandomSessionIdGenerator;

/**
 * Unit test for {@link IdentifierSerializer}.
 *
 * @author Paul Ferraro
 */
public class IdentifierSerializerTestCase {

    @Test public void testUTF8() throws IOException{test(IdentifierSerializer.UTF8,()->UUID.randomUUID().toString());}

    private static void test(Serializer<String> externalizer, Supplier<String> generator) throws IOException {
        for (int i = 0; i < 100; ++i) {
            String id = generator.get();
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            try (ObjectOutputStream output = new ObjectOutputStream(bytes)) {
                externalizer.write(output, id);
            }
            try (ObjectInputStream input = new ObjectInputStream(new ByteArrayInputStream(bytes.toByteArray()))) {
                Assert.assertEquals(id, externalizer.read(input));
            }
        }
    }
}
