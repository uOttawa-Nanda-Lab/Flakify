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
package org.activiti.engine.test.bpmn.event.end;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.ExtensionAttribute;
import org.activiti.bpmn.model.ExtensionElement;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.history.DeleteReason;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**


 */
public class TerminateEndEventTest extends PluggableActivitiTestCase {

  public static int serviceTaskInvokedCount = 0;
  
  public static class CountDelegate implements JavaDelegate {

    public void execute(DelegateExecution execution) {
      serviceTaskInvokedCount++;

      // leave only 3 out of n subprocesses
      execution.setVariableLocal("terminate", serviceTaskInvokedCount > 3);
    }
  }

  public static int serviceTaskInvokedCount2 = 0;

  public static class CountDelegate2 implements JavaDelegate {

    public void execute(DelegateExecution execution) {
      serviceTaskInvokedCount2++;
    }
  }

  @Deployment public void testNestedCallActivitiesTerminateAll(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("TestNestedCallActivities");List<Task> tasks=assertTaskNames(processInstance,Arrays.asList("B","B","B","B","Before A","Before A","Before A","Before A","Before B","Before C"));taskService.complete(tasks.get(8).getId());assertProcessEnded(processInstance.getId());assertHistoricProcessInstanceDetails(processInstance);processInstance=runtimeService.startProcessInstanceByKey("TestNestedCallActivities");tasks=assertTaskNames(processInstance,Arrays.asList("B","B","B","B","Before A","Before A","Before A","Before A","Before B","Before C"));taskService.complete(tasks.get(9).getId());assertProcessEnded(processInstance.getId());assertHistoricProcessInstanceDetails(processInstance);processInstance=runtimeService.startProcessInstanceByKey("TestNestedCallActivities");tasks=assertTaskNames(processInstance,Arrays.asList("B","B","B","B","Before A","Before A","Before A","Before A","Before B","Before C"));taskService.complete(tasks.get(5).getId());Task task=taskService.createTaskQuery().taskName("subprocess1_task").singleResult();assertNotNull(task);taskService.complete(task.getId());assertProcessEnded(processInstance.getId());assertHistoricProcessInstanceDetails(processInstance);}
	
	private List<Task> assertTaskNames(ProcessInstance processInstance, List<String> taskNames) {
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();
		for (int i=0; i<taskNames.size(); i++) {
			assertEquals("Task name at index " + i + " does not match", taskNames.get(i), tasks.get(i).getName());
		}
		return tasks;
	}

  protected void assertHistoricProcessInstanceDetails(ProcessInstance pi) {
    assertHistoricProcessInstanceDetails(pi.getId());
  }
  
  protected void assertHistoricProcessInstanceDetails(String processInstanceId) {
    if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
      HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
          .processInstanceId(processInstanceId).singleResult();
      
      assertNotNull(historicProcessInstance.getEndTime());
      assertNotNull(historicProcessInstance.getDurationInMillis());
      assertNotNull(historicProcessInstance.getEndActivityId());
    }
  }
  
  protected void assertHistoricProcessInstanceDeleteReason(ProcessInstance processInstance, String expectedDeleteReason) {
    if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
      HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
          .processInstanceId(processInstance.getId()).singleResult();
      if (expectedDeleteReason == null) {
        assertNull(historicProcessInstance.getDeleteReason());
      } else {
        assertTrue(historicProcessInstance.getDeleteReason().startsWith(expectedDeleteReason));
      }
    }
  }
  
  protected void assertHistoricTasksDeleteReason(ProcessInstance processInstance, String expectedDeleteReason, String ... taskNames) {
    if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
      for (String taskName : taskNames) {
        List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery()
            .processInstanceId(processInstance.getId()).taskName(taskName).list();
        assertTrue(historicTaskInstances.size() > 0);
        for (HistoricTaskInstance historicTaskInstance : historicTaskInstances) {
          assertNotNull(historicTaskInstance.getEndTime());
          if (expectedDeleteReason == null) {
            assertNull(historicTaskInstance.getDeleteReason());
          } else {
            assertTrue(historicTaskInstance.getDeleteReason().startsWith(expectedDeleteReason));
          }
        }
      }
    }
  }
  
  protected void assertHistoricActivitiesDeleteReason(ProcessInstance processInstance, String expectedDeleteReason, String ... activityIds) {
    if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
      for (String activityId : activityIds) {
        List<HistoricActivityInstance> historicActiviyInstances = historyService.createHistoricActivityInstanceQuery()
            .activityId(activityId).processInstanceId(processInstance.getId()).list();
        assertTrue(historicActiviyInstances.size() > 0);
        for (HistoricActivityInstance historicActiviyInstance : historicActiviyInstances) {
          assertNotNull(historicActiviyInstance.getEndTime());
          if (expectedDeleteReason == null) {
            assertNull(historicActiviyInstance.getDeleteReason()); 
          } else {
            assertTrue(historicActiviyInstance.getDeleteReason().startsWith(expectedDeleteReason));
          }
        }
      }
    }
  }
  
}