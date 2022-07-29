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
package org.activiti.examples.bpmn.event.error;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**

 */
public class BoundaryErrorEventTest extends PluggableActivitiTestCase {

  @Deployment(resources={"org/activiti/examples/bpmn/event/error/reviewSalesLead.bpmn20.xml"}) public void testReviewSalesLeadProcess(){Map<String, Object> variables=new HashMap<String, Object>();variables.put("details","very interesting");variables.put("customerName","Alfresco");String procId=runtimeService.startProcessInstanceByKey("reviewSaledLead",variables).getId();Task task=taskService.createTaskQuery().taskAssignee("kermit").singleResult();assertEquals("Provide new sales lead",task.getName());taskService.complete(task.getId());Task ratingTask=taskService.createTaskQuery().taskCandidateGroup("accountancy").singleResult();assertEquals("Review customer rating",ratingTask.getName());Task profitabilityTask=taskService.createTaskQuery().taskCandidateGroup("management").singleResult();assertEquals("Review profitability",profitabilityTask.getName());variables=new HashMap<String, Object>();variables.put("notEnoughInformation",true);taskService.complete(profitabilityTask.getId(),variables);Task provideDetailsTask=taskService.createTaskQuery().taskAssignee("kermit").singleResult();assertEquals("Provide additional details",provideDetailsTask.getName());taskService.complete(provideDetailsTask.getId());List<Task> reviewTasks=taskService.createTaskQuery().orderByTaskName().asc().list();assertEquals("Review customer rating",reviewTasks.get(0).getName());assertEquals("Review profitability",reviewTasks.get(1).getName());taskService.complete(reviewTasks.get(0).getId());variables.put("notEnoughInformation",false);taskService.complete(reviewTasks.get(1).getId(),variables);assertProcessEnded(procId);}

}
