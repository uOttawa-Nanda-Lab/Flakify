/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.examples.bpmn.scripttask;

import groovy.lang.MissingPropertyException;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.Deployment;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**


 */
public class ScriptTaskTest extends PluggableActivitiTestCase {

  @Deployment public void testAutoStoreVariables(){String id=runtimeService.startProcessInstanceByKey("testAutoStoreVariables",CollectionUtil.map("a",20,"b",22)).getId();assertNull(runtimeService.getVariable(id,"sum"));taskService.complete(taskService.createTaskQuery().singleResult().getId());assertEquals(42,((Number)runtimeService.getVariable(id,"sum")).intValue());}

  protected void verifyExceptionInStacktrace(Exception rootException, Class<?> expectedExceptionClass) {
    Throwable expectedException = rootException;
    boolean found = false;
    while (!found && expectedException != null) {
      if (expectedException.getClass().equals(expectedExceptionClass)) {
        found = true;
      } else {
        expectedException = expectedException.getCause();
      }
    }

    assertEquals(expectedExceptionClass, expectedException.getClass());
  }

}
