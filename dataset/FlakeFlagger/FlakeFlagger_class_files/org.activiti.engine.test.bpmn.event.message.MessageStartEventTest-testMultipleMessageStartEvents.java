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

package org.activiti.engine.test.bpmn.event.message;

import java.util.List;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.EventSubscriptionQueryImpl;
import org.activiti.engine.impl.persistence.entity.EventSubscriptionEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**

 */
public class MessageStartEventTest extends PluggableActivitiTestCase {

  @Deployment public void testMultipleMessageStartEvents(){ProcessInstance processInstance=runtimeService.startProcessInstanceByMessage("newInvoiceMessage");assertFalse(processInstance.isEnded());Task task=taskService.createTaskQuery().taskDefinitionKey("taskAfterMessageStart").singleResult();assertNotNull(task);taskService.complete(task.getId());assertProcessEnded(processInstance.getId());processInstance=runtimeService.startProcessInstanceByMessage("newInvoiceMessage2");assertFalse(processInstance.isEnded());task=taskService.createTaskQuery().taskDefinitionKey("taskAfterMessageStart2").singleResult();assertNotNull(task);taskService.complete(task.getId());assertProcessEnded(processInstance.getId());processInstance=runtimeService.startProcessInstanceByKey("testProcess");assertFalse(processInstance.isEnded());task=taskService.createTaskQuery().taskDefinitionKey("taskAfterMessageStart").singleResult();assertNotNull(task);taskService.complete(task.getId());assertProcessEnded(processInstance.getId());}

}
