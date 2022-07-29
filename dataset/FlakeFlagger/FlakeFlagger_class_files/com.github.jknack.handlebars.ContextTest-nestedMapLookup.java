/**
 * Copyright (c) 2012 Edgar Espina
 * This file is part of Handlebars.java.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jknack.handlebars;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Unit test for {@link Context}.
 *
 * @author edgar.espina
 * @since 0.1.0
 */
public class ContextTest {

  static class Base {
    public String getBaseProperty() {
      return "baseProperty";
    }

    public String getChildProperty() {
      return "baseProperty";
    }
  }

  static class Child extends Base {
    @Override
    public String getChildProperty() {
      return "childProperty";
    }
  }

  @Test public void nestedMapLookup(){Map<String, Object> model=new HashMap<String, Object>();Map<String, Object> nested=new HashMap<String, Object>();model.put("nested",nested);nested.put("simple","value");Context context=Context.newContext(model);assertNotNull(context);assertEquals("value",context.get("nested.simple"));}

}
