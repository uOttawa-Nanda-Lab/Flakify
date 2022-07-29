/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ambari.server.controller.utilities;

import junit.framework.Assert;
import org.apache.ambari.server.controller.internal.ResourceImpl;
import org.apache.ambari.server.controller.spi.Predicate;
import org.apache.ambari.server.controller.spi.Resource;
import org.junit.Test;

/**
 *
 */
public class PredicateBuilderTest {

  @Test public void testAltProperty(){String p1="cat1/prop1";String p2="cat1/prop2";String p3="prop3";Resource resource=new ResourceImpl(Resource.Type.Cluster);resource.setProperty(p1,"foo");resource.setProperty(p2,"bar");resource.setProperty(p3,"cat");PredicateBuilder pb1=new PredicateBuilder();Predicate predicate1=pb1.begin().property("cat1/prop1").equals("foo").and().property("cat1/prop2").equals("bar").end().or().property("prop3").equals("cat").toPredicate();Assert.assertTrue(predicate1.evaluate(resource));}
}
