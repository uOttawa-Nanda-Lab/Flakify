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

package org.activiti.engine.test.api.runtime;

import java.util.List;

import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**

 */
public class InstanceInvolvementTest extends PluggableActivitiTestCase {

  @Deployment(resources={"org/activiti/engine/test/api/runtime/threeParallelTasks.bpmn20.xml"}) public void testInvolvements(){assertNoInvolvement("user1");assertNoInvolvement("user2");assertNoInvolvement("user3");assertNoInvolvement("user4");String instanceId=startProcessAsUser("threeParallelTasks","user1");List<Task> tasks=taskService.createTaskQuery().processInstanceId(instanceId).list();assertEquals(3,tasks.size());assertInvolvement("user1",instanceId);assertNoInvolvement("user2");taskService.claim(tasks.get(0).getId(),"user2");assertInvolvement("user2",instanceId);taskService.complete(tasks.get(0).getId());assertInvolvement("user2",instanceId);completeTaskAsUser(tasks.get(1).getId(),"user3");assertInvolvement("user3",instanceId);runtimeService.addUserIdentityLink(instanceId,"user4","custom");assertInvolvement("user4",instanceId);List<IdentityLink> identityLinks=runtimeService.getIdentityLinksForProcessInstance(instanceId);assertTrue(containsIdentityLink(identityLinks,"user1","starter"));assertTrue(containsIdentityLink(identityLinks,"user2","participant"));assertTrue(containsIdentityLink(identityLinks,"user3","participant"));assertTrue(containsIdentityLink(identityLinks,"user4","custom"));assertEquals(4,identityLinks.size());completeTaskAsUser(tasks.get(2).getId(),"user1");assertNoInvolvement("user1");assertNoInvolvement("user2");assertNoInvolvement("user3");assertNoInvolvement("user4");}

  private void assertNoInvolvement(String userId) {
    assertEquals(0L, runtimeService.createProcessInstanceQuery().involvedUser(userId).count());
  }

  private void assertInvolvement(String userId, String instanceId) {
    ProcessInstance involvedInstance = runtimeService.createProcessInstanceQuery().involvedUser(userId).singleResult();
    assertEquals(instanceId, involvedInstance.getId());
  }

  private String startProcessAsUser(String processId, String userId) {
    try {
      Authentication.setAuthenticatedUserId(userId);
      return runtimeService.startProcessInstanceByKey(processId).getId();
    } finally {
      Authentication.setAuthenticatedUserId(null);
    }
  }

  private void completeTaskAsUser(String taskId, String userId) {
    try {
      Authentication.setAuthenticatedUserId(userId);
      taskService.complete(taskId);
    } finally {
      Authentication.setAuthenticatedUserId(null);
    }
  }

  private boolean containsIdentityLink(List<IdentityLink> identityLinks, String userId, String type) {
    for (IdentityLink identityLink : identityLinks) {
      if (userId.equals(identityLink.getUserId()) && type.equals(identityLink.getType())) {
        return true;
      }
    }
    return false;
  }

}
