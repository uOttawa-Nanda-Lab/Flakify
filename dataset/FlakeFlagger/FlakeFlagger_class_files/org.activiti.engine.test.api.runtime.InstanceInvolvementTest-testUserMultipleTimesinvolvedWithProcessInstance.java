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

  @Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testUserMultipleTimesinvolvedWithProcessInstance(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");runtimeService.addUserIdentityLink(processInstance.getId(),"kermit","type1");runtimeService.addUserIdentityLink(processInstance.getId(),"kermit","type2");assertEquals(1L,runtimeService.createProcessInstanceQuery().involvedUser("kermit").count());}

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
