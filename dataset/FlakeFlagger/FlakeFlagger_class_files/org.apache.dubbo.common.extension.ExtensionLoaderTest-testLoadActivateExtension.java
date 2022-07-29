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
package org.apache.dubbo.common.extension;

import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.activate.ActivateExt1;
import org.apache.dubbo.common.extension.activate.impl.ActivateExt1Impl1;
import org.apache.dubbo.common.extension.activate.impl.GroupActivateExtImpl;
import org.apache.dubbo.common.extension.activate.impl.OldActivateExt1Impl2;
import org.apache.dubbo.common.extension.activate.impl.OldActivateExt1Impl3;
import org.apache.dubbo.common.extension.activate.impl.OrderActivateExtImpl1;
import org.apache.dubbo.common.extension.activate.impl.OrderActivateExtImpl2;
import org.apache.dubbo.common.extension.activate.impl.ValueActivateExtImpl;
import org.apache.dubbo.common.extension.ext1.SimpleExt;
import org.apache.dubbo.common.extension.ext1.impl.SimpleExtImpl1;
import org.apache.dubbo.common.extension.ext1.impl.SimpleExtImpl2;
import org.apache.dubbo.common.extension.ext2.Ext2;
import org.apache.dubbo.common.extension.ext6_wrap.WrappedExt;
import org.apache.dubbo.common.extension.ext6_wrap.impl.Ext5Wrapper1;
import org.apache.dubbo.common.extension.ext6_wrap.impl.Ext5Wrapper2;
import org.apache.dubbo.common.extension.ext7.InitErrorExt;
import org.apache.dubbo.common.extension.ext8_add.AddExt1;
import org.apache.dubbo.common.extension.ext8_add.AddExt2;
import org.apache.dubbo.common.extension.ext8_add.AddExt3;
import org.apache.dubbo.common.extension.ext8_add.AddExt4;
import org.apache.dubbo.common.extension.ext8_add.impl.AddExt1Impl1;
import org.apache.dubbo.common.extension.ext8_add.impl.AddExt1_ManualAdaptive;
import org.apache.dubbo.common.extension.ext8_add.impl.AddExt1_ManualAdd1;
import org.apache.dubbo.common.extension.ext8_add.impl.AddExt1_ManualAdd2;
import org.apache.dubbo.common.extension.ext8_add.impl.AddExt2_ManualAdaptive;
import org.apache.dubbo.common.extension.ext8_add.impl.AddExt3_ManualAdaptive;
import org.apache.dubbo.common.extension.ext8_add.impl.AddExt4_ManualAdaptive;
import org.apache.dubbo.common.extension.ext9_empty.Ext9Empty;
import org.apache.dubbo.common.extension.ext9_empty.impl.Ext9EmptyImpl;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.matchers.JUnitMatchers.containsString;

public class ExtensionLoaderTest {
    @Test public void testLoadActivateExtension() throws Exception{URL url=URL.valueOf("test://localhost/test");List<ActivateExt1> list=ExtensionLoader.getExtensionLoader(ActivateExt1.class).getActivateExtension(url,new String[]{},"default_group");Assert.assertEquals(1,list.size());Assert.assertTrue(list.get(0).getClass() == ActivateExt1Impl1.class);url=url.addParameter(Constants.GROUP_KEY,"group1");list=ExtensionLoader.getExtensionLoader(ActivateExt1.class).getActivateExtension(url,new String[]{},"group1");Assert.assertEquals(1,list.size());Assert.assertTrue(list.get(0).getClass() == GroupActivateExtImpl.class);url=url.addParameter(Constants.GROUP_KEY,"old_group");list=ExtensionLoader.getExtensionLoader(ActivateExt1.class).getActivateExtension(url,new String[]{},"old_group");Assert.assertEquals(2,list.size());Assert.assertTrue(list.get(0).getClass() == OldActivateExt1Impl2.class || list.get(0).getClass() == OldActivateExt1Impl3.class);url=url.removeParameter(Constants.GROUP_KEY);url=url.addParameter(Constants.GROUP_KEY,"value");url=url.addParameter("value","value");list=ExtensionLoader.getExtensionLoader(ActivateExt1.class).getActivateExtension(url,new String[]{},"value");Assert.assertEquals(1,list.size());Assert.assertTrue(list.get(0).getClass() == ValueActivateExtImpl.class);url=URL.valueOf("test://localhost/test");url=url.addParameter(Constants.GROUP_KEY,"order");list=ExtensionLoader.getExtensionLoader(ActivateExt1.class).getActivateExtension(url,new String[]{},"order");Assert.assertEquals(2,list.size());Assert.assertTrue(list.get(0).getClass() == OrderActivateExtImpl1.class);Assert.assertTrue(list.get(1).getClass() == OrderActivateExtImpl2.class);}

}