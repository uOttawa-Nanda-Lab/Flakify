/**
 * Copyright (c) 2012 Edgar Espina
 *
 * This file is part of Handlebars.java.
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
package com.github.jknack.handlebars;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.context.FieldValueResolver;
import com.github.jknack.handlebars.context.JavaBeanValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.context.MethodValueResolver;

/**
 * Unit test for {@link Context}.
 *
 * @author edgar.espina
 * @since 0.1.0
 */
public class ValueResolverTest {

  static class Base {

    String base;

    String child;

    public Base(final String base, final String child) {
      this.base = base;
      this.child = child;
    }

    public String getBaseProperty() {
      return base;
    }

    public String getChildProperty() {
      return child;
    }
  }

  @Test public void javaBeanResolver(){Context context=Context.newBuilder(new Base("a","b")).resolver(JavaBeanValueResolver.INSTANCE).build();assertNotNull(context);assertEquals("a",context.get("baseProperty"));assertEquals("b",context.get("childProperty"));}
}
