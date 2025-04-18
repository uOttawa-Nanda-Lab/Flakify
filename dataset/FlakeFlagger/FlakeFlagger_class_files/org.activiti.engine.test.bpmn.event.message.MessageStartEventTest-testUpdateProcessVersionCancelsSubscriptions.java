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

  public void testUpdateProcessVersionCancelsSubscriptions(){String deploymentId=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/message/MessageStartEventTest.testSingleMessageStartEvent.bpmn20.xml").deploy().getId();List<EventSubscriptionEntity> eventSubscriptions=new EventSubscriptionQueryImpl(processEngineConfiguration.getCommandExecutor()).list();List<ProcessDefinition> processDefinitions=repositoryService.createProcessDefinitionQuery().list();assertEquals(1,eventSubscriptions.size());assertEquals(1,processDefinitions.size());String newDeploymentId=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/message/MessageStartEventTest.testSingleMessageStartEvent.bpmn20.xml").deploy().getId();List<EventSubscriptionEntity> newEventSubscriptions=new EventSubscriptionQueryImpl(processEngineConfiguration.getCommandExecutor()).list();List<ProcessDefinition> newProcessDefinitions=repositoryService.createProcessDefinitionQuery().list();assertEquals(1,newEventSubscriptions.size());assertEquals(2,newProcessDefinitions.size());for (ProcessDefinition processDefinition:newProcessDefinitions){if (processDefinition.getVersion() == 1){for (EventSubscriptionEntity subscription:newEventSubscriptions){assertFalse(subscription.getConfiguration().equals(processDefinition.getId()));}} else {for (EventSubscriptionEntity subscription:newEventSubscriptions){assertTrue(subscription.getConfiguration().equals(processDefinition.getId()));}}}assertFalse(eventSubscriptions.equals(newEventSubscriptions));repositoryService.deleteDeployment(deploymentId);repositoryService.deleteDeployment(newDeploymentId);}

}
