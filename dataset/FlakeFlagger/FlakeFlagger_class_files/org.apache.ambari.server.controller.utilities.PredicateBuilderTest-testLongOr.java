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

  @Test public void testLongOr(){String p1=PropertyHelper.getPropertyId("cat1","prop1");String p2=PropertyHelper.getPropertyId("cat1","prop2");String p3=PropertyHelper.getPropertyId("cat1","prop3");String p4=PropertyHelper.getPropertyId("cat1","prop4");Resource resource=new ResourceImpl(Resource.Type.Cluster);resource.setProperty(p1,"foo");resource.setProperty(p2,"bar");resource.setProperty(p3,"cat");resource.setProperty(p4,"dog");PredicateBuilder pb=new PredicateBuilder();Predicate predicate1=pb.property(p1).equals("foo").or().property(p2).equals("bar").or().property(p3).equals("cat").or().property(p4).equals("dog").toPredicate();Assert.assertTrue(predicate1.evaluate(resource));PredicateBuilder pb2=new PredicateBuilder();Predicate predicate2=pb2.property(p1).equals("foo").or().property(p2).equals("car").or().property(p3).equals("cat").or().property(p4).equals("dog").toPredicate();Assert.assertTrue(predicate2.evaluate(resource));PredicateBuilder pb3=new PredicateBuilder();Predicate predicate3=pb3.property(p1).equals("fun").or().property(p2).equals("car").or().property(p3).equals("bat").or().property(p4).equals("dot").toPredicate();Assert.assertFalse(predicate3.evaluate(resource));}
}
