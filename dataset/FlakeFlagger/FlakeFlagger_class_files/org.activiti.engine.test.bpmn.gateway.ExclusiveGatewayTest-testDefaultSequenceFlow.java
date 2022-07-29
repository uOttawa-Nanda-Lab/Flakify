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
package org.activiti.engine.test.bpmn.gateway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**

 */
public class ExclusiveGatewayTest extends PluggableActivitiTestCase {

  @Deployment public void testDefaultSequenceFlow(){String procId=runtimeService.startProcessInstanceByKey("exclusiveGwDefaultSequenceFlow",CollectionUtil.singletonMap("input",1)).getId();Task task=taskService.createTaskQuery().singleResult();assertEquals("Input is one",task.getName());runtimeService.deleteProcessInstance(procId,null);runtimeService.startProcessInstanceByKey("exclusiveGwDefaultSequenceFlow",CollectionUtil.singletonMap("input",5)).getId();task=taskService.createTaskQuery().singleResult();assertEquals("Default input",task.getName());}

}
