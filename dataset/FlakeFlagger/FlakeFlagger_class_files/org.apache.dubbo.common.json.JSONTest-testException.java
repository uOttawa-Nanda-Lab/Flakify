/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.common.json;

import org.junit.Assert;
import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@Deprecated
public class JSONTest {
    static byte[] DEFAULT_BYTES = {3, 12, 14, 41, 12, 2, 3, 12, 4, 67, 23};
    static int DEFAULT_$$ = 152;

    @Test public void testException() throws Exception{MyException e=new MyException("001","AAAAAAAA");StringWriter writer=new StringWriter();JSON.json(e,writer);String json=writer.getBuffer().toString();System.out.println(json);StringReader reader=new StringReader(json);MyException result=JSON.parse(reader,MyException.class);Assert.assertEquals("001",result.getCode());Assert.assertEquals("AAAAAAAA",result.getMessage());}

    public static class Bean1 {
        public int[] array;
        private String name, displayName;

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Bean {
        public int[] array;
        public boolean $b;
        public int $$ = DEFAULT_$$;
        public byte[] bytes = DEFAULT_BYTES;
        private String name, displayName = "钱磊";

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}