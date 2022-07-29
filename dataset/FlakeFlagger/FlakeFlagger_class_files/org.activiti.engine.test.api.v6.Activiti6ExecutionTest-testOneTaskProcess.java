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
package org.activiti.engine.test.api.v6;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.delegate.event.ActivitiActivityCancelledEvent;
import org.activiti.engine.delegate.event.ActivitiActivityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.junit.Test;

public class Activiti6ExecutionTest extends PluggableActivitiTestCase {

  @Test @Deployment public void testOneTaskProcess(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");List<Execution> executionList=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).list();assertEquals(2,executionList.size());Execution rootProcessInstance=null;Execution childExecution=null;for (Execution execution:executionList){if (execution.getId().equals(execution.getProcessInstanceId())){rootProcessInstance=execution;assertNull(execution.getActivityId());} else {childExecution=execution;assertTrue(execution.getId().equals(execution.getProcessInstanceId()) == false);assertEquals("theTask",execution.getActivityId());}}assertNotNull(rootProcessInstance);assertNotNull(childExecution);Task task=taskService.createTaskQuery().singleResult();assertEquals(childExecution.getId(),task.getExecutionId());taskService.complete(task.getId());if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)){List<HistoricActivityInstance> historicActivities=historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId()).list();assertEquals(3,historicActivities.size());List<String> activityIds=new ArrayList<String>();activityIds.add("theStart");activityIds.add("theTask");activityIds.add("theEnd");for (HistoricActivityInstance historicActivityInstance:historicActivities){activityIds.remove(historicActivityInstance.getActivityId());assertEquals(childExecution.getId(),historicActivityInstance.getExecutionId());}assertEquals(0,activityIds.size());}}
  
  public class SubProcessEventListener implements ActivitiEventListener {
 
    private List<ActivitiEvent> eventsReceived;

    public SubProcessEventListener() {
      eventsReceived = new ArrayList<ActivitiEvent>();
    }

    public List<ActivitiEvent> getEventsReceived() {
      return eventsReceived;
    }

    public void clearEventsReceived() {
      eventsReceived.clear();
    }

    @Override
    public void onEvent(ActivitiEvent activitiEvent) {
      if (activitiEvent instanceof ActivitiActivityEvent) {
        ActivitiActivityEvent event = (ActivitiActivityEvent) activitiEvent;
        if ("subProcess".equals(event.getActivityType())) {
          eventsReceived.add(event);
        }
      } else if (activitiEvent instanceof ActivitiActivityCancelledEvent) {
        ActivitiActivityCancelledEvent event = (ActivitiActivityCancelledEvent) activitiEvent;
        if ("subProcess".equals(event.getActivityType())) {
          eventsReceived.add(event);
        }
      }
    }

    @Override
    public boolean isFailOnException() {
      return true;
    }
  }
}
