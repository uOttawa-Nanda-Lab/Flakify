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
package org.activiti.engine.test.api.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.engine.test.Deployment;

import com.fasterxml.jackson.databind.node.ObjectNode;

import org.activiti.api.runtime.shared.identity.UserGroupManager;
import org.mockito.Mockito;

/**



 */
public class TaskQueryTest extends PluggableActivitiTestCase {

  private List<String> taskIds;

  private static final String KERMIT = "kermit";
  private static final List<String> KERMITSGROUPS = Arrays.asList("management","accountancy");

  private static final String GONZO = "gonzo";
  private static final List<String> GONZOSGROUPS = Arrays.asList();

  private static final String FOZZIE = "fozzie";
  private static final List<String> FOZZIESGROUPS = Arrays.asList("management");

  private UserGroupManager userGroupManager = Mockito.mock(UserGroupManager.class);


  @Deployment public void testTaskVariableValueEquals() throws Exception{ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals("unexistingVar","value").count());Map<String, Object> variables=new HashMap<String, Object>();variables.put("longVar",928374L);variables.put("shortVar",(short)123);variables.put("integerVar",1234);variables.put("stringVar","stringValue");variables.put("booleanVar",true);Date date=Calendar.getInstance().getTime();variables.put("dateVar",date);variables.put("nullVar",null);taskService.setVariablesLocal(task.getId(),variables);assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals("longVar",928374L).count());assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals("shortVar",(short)123).count());assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals("integerVar",1234).count());assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals("stringVar","stringValue").count());assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals("booleanVar",true).count());assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals("dateVar",date).count());assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals("nullVar",null).count());assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals("longVar",999L).count());assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals("shortVar",(short)999).count());assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals("integerVar",999).count());assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals("stringVar","999").count());assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals("booleanVar",false).count());Calendar otherDate=Calendar.getInstance();otherDate.add(Calendar.YEAR,1);assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals("dateVar",otherDate.getTime()).count());assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals("nullVar","999").count());assertEquals(1,taskService.createTaskQuery().taskVariableValueNotEquals("longVar",999L).count());assertEquals(1,taskService.createTaskQuery().taskVariableValueNotEquals("shortVar",(short)999).count());assertEquals(1,taskService.createTaskQuery().taskVariableValueNotEquals("integerVar",999).count());assertEquals(1,taskService.createTaskQuery().taskVariableValueNotEquals("stringVar","999").count());assertEquals(1,taskService.createTaskQuery().taskVariableValueNotEquals("booleanVar",false).count());assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals(928374L).count());assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals((short)123).count());assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals(1234).count());assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals("stringValue").count());assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals(true).count());assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals(date).count());assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals(null).count());assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals(999999L).count());assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals((short)999).count());assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals(9999).count());assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals("unexistingstringvalue").count());assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals(false).count());assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals(otherDate.getTime()).count());assertEquals(1,taskService.createTaskQuery().taskVariableValueLike("stringVar","string%").count());assertEquals(0,taskService.createTaskQuery().taskVariableValueLike("stringVar","String%").count());assertEquals(1,taskService.createTaskQuery().taskVariableValueLike("stringVar","%Value").count());assertEquals(1,taskService.createTaskQuery().taskVariableValueGreaterThan("integerVar",1000).count());assertEquals(0,taskService.createTaskQuery().taskVariableValueGreaterThan("integerVar",1234).count());assertEquals(0,taskService.createTaskQuery().taskVariableValueGreaterThan("integerVar",1240).count());assertEquals(1,taskService.createTaskQuery().taskVariableValueGreaterThanOrEqual("integerVar",1000).count());assertEquals(1,taskService.createTaskQuery().taskVariableValueGreaterThanOrEqual("integerVar",1234).count());assertEquals(0,taskService.createTaskQuery().taskVariableValueGreaterThanOrEqual("integerVar",1240).count());assertEquals(1,taskService.createTaskQuery().taskVariableValueLessThan("integerVar",1240).count());assertEquals(0,taskService.createTaskQuery().taskVariableValueLessThan("integerVar",1234).count());assertEquals(0,taskService.createTaskQuery().taskVariableValueLessThan("integerVar",1000).count());assertEquals(1,taskService.createTaskQuery().taskVariableValueLessThanOrEqual("integerVar",1240).count());assertEquals(1,taskService.createTaskQuery().taskVariableValueLessThanOrEqual("integerVar",1234).count());assertEquals(0,taskService.createTaskQuery().taskVariableValueLessThanOrEqual("integerVar",1000).count());}

  /**
   * Generates some test tasks. - 6 tasks where kermit is a candidate - 1 tasks where gonzo is assignee - 2 tasks assigned to management group - 2 tasks assigned to accountancy group - 1 task assigned
   * to both the management and accountancy group
   */
  private List<String> generateTestTasks() throws Exception {
    List<String> ids = new ArrayList<String>();

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
    // 6 tasks for kermit
    processEngineConfiguration.getClock().setCurrentTime(sdf.parse("01/01/2001 01:01:01.000"));
    for (int i = 0; i < 6; i++) {
      Task task = taskService.newTask();
      task.setName("testTask");
      task.setDescription("testTask description");
      task.setOwner(GONZO);
      task.setPriority(3);
      taskService.saveTask(task);
      ids.add(task.getId());
      taskService.addCandidateUser(task.getId(), KERMIT);
    }

    processEngineConfiguration.getClock().setCurrentTime(sdf.parse("02/02/2002 02:02:02.000"));
    // 1 task for gonzo
    Task task = taskService.newTask();
    task.setName("gonzoTask");
    task.setDescription("gonzo description");
    task.setPriority(4);
    taskService.saveTask(task);
    taskService.setAssignee(task.getId(), GONZO);
    taskService.setVariable(task.getId(), "testVar", "someVariable");
    ids.add(task.getId());

    processEngineConfiguration.getClock().setCurrentTime(sdf.parse("03/03/2003 03:03:03.000"));
    // 2 tasks for management group
    for (int i = 0; i < 2; i++) {
      task = taskService.newTask();
      task.setName("managementTask");
      task.setPriority(10);
      taskService.saveTask(task);
      taskService.addCandidateGroup(task.getId(), "management");
      ids.add(task.getId());
    }

    processEngineConfiguration.getClock().setCurrentTime(sdf.parse("04/04/2004 04:04:04.000"));
    // 2 tasks for accountancy group
    for (int i = 0; i < 2; i++) {
      task = taskService.newTask();
      task.setName("accountancyTask");
      task.setDescription("accountancy description");
      taskService.saveTask(task);
      taskService.addCandidateGroup(task.getId(), "accountancy");
      ids.add(task.getId());
    }

    processEngineConfiguration.getClock().setCurrentTime(sdf.parse("05/05/2005 05:05:05.000"));
    // 1 task assigned to management and accountancy group
    task = taskService.newTask();
    task.setName("managementAndAccountancyTask");
    taskService.saveTask(task);
    taskService.addCandidateGroup(task.getId(), "management");
    taskService.addCandidateGroup(task.getId(), "accountancy");
    ids.add(task.getId());

    return ids;
  }

}
