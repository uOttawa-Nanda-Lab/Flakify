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
package org.apache.ambari.server.api.services;

import org.apache.ambari.server.api.resources.ResourceInstance;
import org.apache.ambari.server.controller.internal.TemporalInfoImpl;
import org.apache.ambari.server.controller.predicate.*;
import org.apache.ambari.server.controller.spi.Predicate;
import org.apache.ambari.server.controller.spi.TemporalInfo;
import org.apache.ambari.server.controller.utilities.PropertyHelper;
import org.junit.Test;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Request tests.
 */
public class RequestImplTest {

  @Test public void testGetQueryPredicate(){String uri="http://foo.bar.com/api/v1/clusters?foo=bar&orProp1=5|orProp2!=6|orProp3<100&prop!=5&prop2>10&prop3>=20&prop4<500&prop5<=1&fields=field1,category/field2";Request request=new TestRequest(null,null,null,Request.Type.GET,null,uri);Predicate predicate=request.getQueryPredicate();Set<BasePredicate> setPredicates=new HashSet<BasePredicate>();setPredicates.add(new EqualsPredicate<String>("foo","bar"));Set<BasePredicate> setOrPredicates=new HashSet<BasePredicate>();setOrPredicates.add(new EqualsPredicate<String>("orProp1","5"));setOrPredicates.add(new NotPredicate(new EqualsPredicate<String>("orProp2","6")));setOrPredicates.add(new LessPredicate<String>("orProp3","100"));setPredicates.add(new OrPredicate(setOrPredicates.toArray(new BasePredicate[3])));setPredicates.add(new NotPredicate(new EqualsPredicate<String>("prop","5")));setPredicates.add(new GreaterPredicate<String>("prop2","10"));setPredicates.add(new GreaterEqualsPredicate<String>("prop3","20"));setPredicates.add(new LessPredicate<String>("prop4","500"));setPredicates.add(new LessEqualsPredicate<String>("prop5","1"));Predicate expectedPredicate=new AndPredicate(setPredicates.toArray(new BasePredicate[6]));assertEquals(expectedPredicate,predicate);}

  private class TestRequest extends RequestImpl {
    private String m_uri;
    private TestRequest(HttpHeaders headers, String body, UriInfo uriInfo, Type requestType,
                        ResourceInstance resource, String uri) {

      super(headers, body, uriInfo, requestType, resource);
      m_uri = uri;
    }

    @Override
    public String getURI() {
      return m_uri;
    }
  }
}
