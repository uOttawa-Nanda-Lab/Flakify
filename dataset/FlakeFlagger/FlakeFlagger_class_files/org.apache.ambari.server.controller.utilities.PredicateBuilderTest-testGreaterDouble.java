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

  @Test public void testGreaterDouble(){String p1=PropertyHelper.getPropertyId("cat1","prop1");Resource resource=new ResourceImpl(Resource.Type.Cluster);resource.setProperty(p1,2.999);PredicateBuilder pb=new PredicateBuilder();Predicate predicate1=pb.property(p1).greaterThan(1.999).toPredicate();Assert.assertTrue(predicate1.evaluate(resource));PredicateBuilder pb2=new PredicateBuilder();Predicate predicate2=pb2.property(p1).greaterThan(99.998).toPredicate();Assert.assertFalse(predicate2.evaluate(resource));}
}
