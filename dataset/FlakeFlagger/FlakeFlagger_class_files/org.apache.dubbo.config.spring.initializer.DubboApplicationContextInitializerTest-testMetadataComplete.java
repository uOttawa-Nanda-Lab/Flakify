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
package org.apache.dubbo.config.spring.initializer;

import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.ContextConfig;
import org.apache.catalina.startup.Tomcat;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.context.ContextLoaderListener;


public class DubboApplicationContextInitializerTest {

    @Test public void testMetadataComplete() throws Exception{Tomcat tomcat=new Tomcat();tomcat.setBaseDir("src/test/resources");tomcat.setPort(12345);StandardContext context=new StandardContext();context.setName("test3");context.setDocBase("test3");context.setPath("/test3");context.addLifecycleListener(new ContextConfig());tomcat.getHost().addChild(context);tomcat.start();Assert.assertEquals(0,context.getApplicationLifecycleListeners().length);tomcat.stop();tomcat.destroy();}

}
