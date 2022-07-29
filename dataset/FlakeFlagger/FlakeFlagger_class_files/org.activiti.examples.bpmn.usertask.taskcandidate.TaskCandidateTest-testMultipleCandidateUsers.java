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
package org.activiti.examples.bpmn.usertask.taskcandidate;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**

 */
public class TaskCandidateTest extends PluggableActivitiTestCase {

  private static final String KERMIT = "kermit";
  private static final List<String> KERMITSGROUPS = Arrays.asList("accountancy");

  private static final String GONZO = "gonzo";
  private static final List<String> GONZOSGROUPS = Arrays.asList("management","accountancy","sales");



  @Deployment public void testMultipleCandidateUsers(){runtimeService.startProcessInstanceByKey("multipleCandidateUsersExample",Collections.singletonMap("Variable",(Object)"var"));assertEquals(1,taskService.createTaskQuery().taskCandidateUser(GONZO,GONZOSGROUPS).list().size());assertEquals(1,taskService.createTaskQuery().taskCandidateUser(KERMIT,KERMITSGROUPS).list().size());List<Task> tasks=taskService.createTaskQuery().taskInvolvedUser(KERMIT).list();assertEquals(1,tasks.size());Task task=tasks.get(0);taskService.setVariableLocal(task.getId(),"taskVar",123);tasks=taskService.createTaskQuery().taskInvolvedUser(KERMIT).includeProcessVariables().includeTaskLocalVariables().list();task=tasks.get(0);assertEquals(1,task.getProcessVariables().size());assertEquals(1,task.getTaskLocalVariables().size());taskService.addUserIdentityLink(task.getId(),GONZO,"test");tasks=taskService.createTaskQuery().taskInvolvedUser(GONZO).includeProcessVariables().includeTaskLocalVariables().list();assertEquals(1,tasks.size());assertEquals(1,task.getProcessVariables().size());assertEquals(1,task.getTaskLocalVariables().size());}

}
