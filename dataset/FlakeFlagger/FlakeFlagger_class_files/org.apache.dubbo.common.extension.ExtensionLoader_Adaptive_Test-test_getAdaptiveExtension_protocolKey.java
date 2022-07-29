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

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.adaptive.HasAdaptiveExt;
import org.apache.dubbo.common.extension.adaptive.impl.HasAdaptiveExt_ManualAdaptive;
import org.apache.dubbo.common.extension.ext1.SimpleExt;
import org.apache.dubbo.common.extension.ext2.Ext2;
import org.apache.dubbo.common.extension.ext2.UrlHolder;
import org.apache.dubbo.common.extension.ext3.UseProtocolKeyExt;
import org.apache.dubbo.common.extension.ext4.NoUrlParamExt;
import org.apache.dubbo.common.extension.ext5.NoAdaptiveMethodExt;
import org.apache.dubbo.common.extension.ext6_inject.Ext6;
import org.apache.dubbo.common.extension.ext6_inject.impl.Ext6Impl2;
import org.apache.dubbo.common.utils.LogUtil;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.allOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.matchers.JUnitMatchers.containsString;

public class ExtensionLoader_Adaptive_Test {

    @Test public void test_getAdaptiveExtension_protocolKey() throws Exception{UseProtocolKeyExt ext=ExtensionLoader.getExtensionLoader(UseProtocolKeyExt.class).getAdaptiveExtension();{String echo=ext.echo(URL.valueOf("1.2.3.4:20880"),"s");assertEquals("Ext3Impl1-echo",echo);Map<String, String> map=new HashMap<String, String>();URL url=new URL("impl3","1.2.3.4",1010,"path1",map);echo=ext.echo(url,"s");assertEquals("Ext3Impl3-echo",echo);url=url.addParameter("key1","impl2");echo=ext.echo(url,"s");assertEquals("Ext3Impl2-echo",echo);}{Map<String, String> map=new HashMap<String, String>();URL url=new URL(null,"1.2.3.4",1010,"path1",map);String yell=ext.yell(url,"s");assertEquals("Ext3Impl1-yell",yell);url=url.addParameter("key2","impl2");yell=ext.yell(url,"s");assertEquals("Ext3Impl2-yell",yell);url=url.setProtocol("impl3");yell=ext.yell(url,"d");assertEquals("Ext3Impl3-yell",yell);}}
}