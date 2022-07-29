/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.http.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link LangUtils}.
 *
 */
public class TestLangUtils {

    @Test public void testBooleanHash(){int h1=LangUtils.hashCode(LangUtils.HASH_SEED,true);int h2=LangUtils.hashCode(LangUtils.HASH_SEED,false);int h3=LangUtils.hashCode(LangUtils.HASH_SEED,true);int h4=LangUtils.hashCode(LangUtils.HASH_SEED,false);Assert.assertTrue(h1 != h2);Assert.assertTrue(h1 == h3);Assert.assertTrue(h2 == h4);}
}
