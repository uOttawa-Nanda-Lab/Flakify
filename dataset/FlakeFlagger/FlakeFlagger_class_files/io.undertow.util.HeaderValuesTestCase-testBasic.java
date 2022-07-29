/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2013 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.undertow.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:david.lloyd@redhat.com">David M. Lloyd</a>
 */
public final class HeaderValuesTestCase {

    @Test
    public void testBasic() {
        final HeaderValues headerValues = new HeaderValues(Headers.DEFLATE);
        assertEquals(0, headerValues.size());
        assertTrue(headerValues.isEmpty());
        assertFalse(headerValues.iterator().hasNext());
        assertFalse(headerValues.descendingIterator().hasNext());
        assertFalse(headerValues.listIterator().hasNext());
        assertFalse(headerValues.listIterator(0).hasNext());
        assertNull(headerValues.peek());
        assertNull(headerValues.peekFirst());
        assertNull(headerValues.peekLast());
    }

}
