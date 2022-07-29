/**
 * Copyright (C) 2013 the original author or authors.
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

package ninja.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;

public class NinjaPropertiesImplTest {

    @Test(expected=RuntimeException.class) public void testGetOrDie(){NinjaPropertiesImpl ninjaPropertiesImpl=new NinjaPropertiesImpl(NinjaMode.dev);assertEquals("dev_testproperty",ninjaPropertiesImpl.getOrDie("testproperty"));ninjaPropertiesImpl.getOrDie("a_propert_that_is_not_in_the_file");}

    public static class MockService {
        @Inject
        @Named(NinjaConstant.applicationSecret)
        public String applicationSecret;
    }

}
