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
package org.apache.dubbo.common.bytecode;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class WrapperTest {
    @Test(expected=NoSuchPropertyException.class) public void testSetPropertyValue() throws Exception{Wrapper w=Wrapper.getWrapper(Object.class);w.setPropertyValue(null,null,null);}

    public static interface I0 {
        String getName();
    }

    public static interface I1 extends I0 {
        void setName(String name);

        void hello(String name);

        int showInt(int v);

        float getFloat();

        void setFloat(float f);
    }

    public static interface EmptyService {
    }

    public static interface Parent1 {
        void hello();
    }


    public static interface Parent2 {
        void world();
    }

    public static interface Son extends Parent1, Parent2 {

    }

    public static class Impl0 {
        public float a, b, c;
    }

    public static class Impl1 implements I1 {
        private String name = "you name";

        private float fv = 0;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void hello(String name) {
            System.out.println("hello " + name);
        }

        public int showInt(int v) {
            return v;
        }

        public float getFloat() {
            return fv;
        }

        public void setFloat(float f) {
            fv = f;
        }
    }

    public static class EmptyServiceImpl implements EmptyService {
    }
}