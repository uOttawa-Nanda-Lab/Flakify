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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.EventSubscriptionQueryImpl;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**

 */
public class MessageIntermediateEventTest extends PluggableActivitiTestCase {

  @Deployment public void testAsyncTriggeredMessageEvent(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("process");assertNotNull(processInstance);Execution execution=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).messageEventSubscriptionName("newMessage").singleResult();assertNotNull(execution);assertEquals(1,createEventSubscriptionQuery().count());assertEquals(2,runtimeService.createExecutionQuery().count());runtimeService.messageEventReceivedAsync("newMessage",execution.getId());assertEquals(1,managementService.createJobQuery().messages().count());waitForJobExecutorToProcessAllJobs(8000L,200L);assertEquals(0,createEventSubscriptionQuery().count());assertEquals(0,runtimeService.createProcessInstanceQuery().count());assertEquals(0,managementService.createJobQuery().count());}

  private EventSubscriptionQueryImpl createEventSubscriptionQuery() {
    return new EventSubscriptionQueryImpl(processEngineConfiguration.getCommandExecutor());
  }
}
