/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ninja;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ninja.utils.ResponseStreams;

import org.apache.commons.io.ByteOrderMark;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Maps;

import ch.qos.logback.core.db.dialect.MySQLDialect;

@RunWith(MockitoJUnitRunner.class)
public class ResultTest {
    
    @Mock
    Context context;
    
    @Mock
    ResponseStreams responseStreams;

    @Test public void testRenderMultipleObjects(){TestObject testObject=new TestObject();Result result=new Result(200);result.render(testObject);assertEquals(testObject,result.getRenderable());String string=new String("test");result.render(string);assertTrue(result.getRenderable() instanceof Map);Map<String, Object> resultMap=(Map)result.getRenderable();assertEquals(string,resultMap.get("string"));assertEquals(testObject,resultMap.get("testObject"));TestObject anotherObject=new TestObject();boolean gotException=false;try {result.render(anotherObject);} catch (IllegalArgumentException e){gotException=true;}assertTrue(gotException);Entry<String, Object> entry=new AbstractMap.SimpleImmutableEntry<String, Object>("anotherObject",anotherObject);result.render(entry);resultMap=(Map)result.getRenderable();assertEquals(3,resultMap.size());assertEquals(anotherObject,resultMap.get("anotherObject"));Map<String, Object> mapToRender=Maps.newHashMap();String anotherString=new String("anotherString");TestObject anotherTestObject=new TestObject();mapToRender.put("anotherString",anotherString);mapToRender.put("anotherTestObject",anotherTestObject);result.render(mapToRender);resultMap=(Map)result.getRenderable();assertEquals(2,resultMap.size());assertEquals(anotherString,resultMap.get("anotherString"));assertEquals(anotherTestObject,resultMap.get("anotherTestObject"));}
    
    /**
     * Simple helper to test if objects get copied to result.
     * 
     */
    public class TestObject {
    }

}
