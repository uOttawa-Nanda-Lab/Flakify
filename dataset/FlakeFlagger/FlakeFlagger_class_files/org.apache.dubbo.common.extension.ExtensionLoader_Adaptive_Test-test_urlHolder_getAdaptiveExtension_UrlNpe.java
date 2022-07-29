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

    @Test public void test_urlHolder_getAdaptiveExtension_UrlNpe() throws Exception{Ext2 ext=ExtensionLoader.getExtensionLoader(Ext2.class).getAdaptiveExtension();try {ext.echo(null,"haha");fail();} catch (IllegalArgumentException e){assertEquals("org.apache.dubbo.common.extension.ext2.UrlHolder argument == null",e.getMessage());}try {ext.echo(new UrlHolder(),"haha");fail();} catch (IllegalArgumentException e){assertEquals("org.apache.dubbo.common.extension.ext2.UrlHolder argument getUrl() == null",e.getMessage());}}
}