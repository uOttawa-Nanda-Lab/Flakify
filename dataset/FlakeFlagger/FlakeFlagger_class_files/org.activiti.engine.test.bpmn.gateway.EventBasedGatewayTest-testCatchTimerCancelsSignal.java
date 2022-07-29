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

import java.util.Date;

import org.activiti.engine.history.DeleteReason;
import org.activiti.engine.impl.EventSubscriptionQueryImpl;
import org.activiti.engine.impl.persistence.entity.EventSubscriptionEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

public class EventBasedGatewayTest extends PluggableActivitiTestCase {

    @Deployment(resources={"org/activiti/engine/test/bpmn/gateway/EventBasedGatewayTest.testCatchAlertAndTimer.bpmn20.xml"}) public void testCatchTimerCancelsSignal(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("catchSignal");assertEquals(1,createEventSubscriptionQuery().count());assertEquals(1,runtimeService.createProcessInstanceQuery().count());assertEquals(1,managementService.createTimerJobQuery().count());processEngineConfiguration.getClock().setCurrentTime(new Date(processEngineConfiguration.getClock().getCurrentTime().getTime() + 10000));waitForJobExecutorToProcessAllJobs(10000,100);assertEquals(0,createEventSubscriptionQuery().count());assertEquals(1,runtimeService.createProcessInstanceQuery().count());assertEquals(0,managementService.createJobQuery().count());assertEquals(0,managementService.createTimerJobQuery().count());Task task=taskService.createTaskQuery().taskName("afterTimer").singleResult();assertNotNull(task);taskService.complete(task.getId());assertHistoricActivitiesDeleteReason(processInstance,DeleteReason.EVENT_BASED_GATEWAY_CANCEL,"signalEvent");}

    private EventSubscriptionQueryImpl createEventSubscriptionQuery() {
        return new EventSubscriptionQueryImpl(processEngineConfiguration.getCommandExecutor());
    }
}
