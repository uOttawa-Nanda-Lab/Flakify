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

  @Deployment public void testDecideBasedOnListOrArrayOfBeans(){List<ExclusiveGatewayTestOrder> orders=new ArrayList<ExclusiveGatewayTestOrder>();orders.add(new ExclusiveGatewayTestOrder(50));orders.add(new ExclusiveGatewayTestOrder(300));orders.add(new ExclusiveGatewayTestOrder(175));ProcessInstance pi=runtimeService.startProcessInstanceByKey("decisionBasedOnListOrArrayOfBeans",CollectionUtil.singletonMap("orders",orders));Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();assertNotNull(task);assertEquals("Gold Member service",task.getName());ExclusiveGatewayTestOrder[] orderArray=orders.toArray(new ExclusiveGatewayTestOrder[orders.size()]);orderArray[1].setPrice(10);pi=runtimeService.startProcessInstanceByKey("decisionBasedOnListOrArrayOfBeans",CollectionUtil.singletonMap("orders",orderArray));task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();assertNotNull(task);assertEquals("Basic service",task.getName());}

}
