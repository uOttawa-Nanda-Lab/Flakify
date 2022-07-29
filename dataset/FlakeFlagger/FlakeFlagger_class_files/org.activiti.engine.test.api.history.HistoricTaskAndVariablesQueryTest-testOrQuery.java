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
package org.activiti.engine.test.api.history;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.api.runtime.shared.identity.UserGroupManager;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.mockito.Mockito;

/**

 */
public class HistoricTaskAndVariablesQueryTest extends PluggableActivitiTestCase {

    private List<String> taskIds;

    private static final String KERMIT = "kermit";
    private static final List<String> KERMITSGROUPS = Arrays.asList("management",
                                                                    "accountancy");

    private static final String GONZO = "gonzo";
    private static final List<String> GONZOSGROUPS = Arrays.asList();

    private static final String FOZZIE = "fozzie";
    private static final List<String> FOZZIESGROUPS = Arrays.asList("management");

    private UserGroupManager userGroupManager = Mockito.mock(UserGroupManager.class);

    @Deployment
    public void testOrQuery() {
        if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
            HistoricTaskInstance task = historyService.createHistoricTaskInstanceQuery()
                    .includeTaskLocalVariables()
                    .or()
                    .taskAssignee(GONZO)
                    .endOr()
                    .singleResult();

            Map<String, Object> variableMap = task.getTaskLocalVariables();
            assertEquals(2,
                         variableMap.size());
            assertEquals(0,
                         task.getProcessVariables().size());
            assertNotNull(variableMap.get("testVar"));
            assertEquals("someVariable",
                         variableMap.get("testVar"));
            assertNotNull(variableMap.get("testVar2"));
            assertEquals(123,
                         variableMap.get("testVar2"));

            List<HistoricTaskInstance> tasks = historyService.createHistoricTaskInstanceQuery().list();
            assertEquals(3,
                         tasks.size());

            task = historyService.createHistoricTaskInstanceQuery().includeProcessVariables().or().taskAssignee(GONZO).taskVariableValueEquals("localVar",
                                                                                                                                               "nonExisting").endOr().singleResult();
            assertEquals(0,
                         task.getProcessVariables().size());
            assertEquals(0,
                         task.getTaskLocalVariables().size());

            Map<String, Object> startMap = new HashMap<String, Object>();
            startMap.put("processVar",
                         true);
            runtimeService.startProcessInstanceByKey("oneTaskProcess",
                                                     startMap);

            task = historyService.createHistoricTaskInstanceQuery().includeProcessVariables().or().taskAssignee(KERMIT).taskVariableValueEquals("localVar",
                                                                                                                                                "nonExisting").endOr().singleResult();
            assertEquals(1,
                         task.getProcessVariables().size());
            assertEquals(0,
                         task.getTaskLocalVariables().size());
            assertTrue((Boolean) task.getProcessVariables().get("processVar"));

            task = historyService.createHistoricTaskInstanceQuery()
                    .includeProcessVariables()
                    .or()
                    .taskAssignee(KERMIT)
                    .taskVariableValueEquals("localVar",
                                             "nonExisting")
                    .endOr()
                    .or()
                    .processDefinitionKey("oneTaskProcess")
                    .taskVariableValueEquals("localVar",
                                             "nonExisting")
                    .endOr()
                    .singleResult();

            assertNotNull(task);
            assertEquals(1,
                         task.getProcessVariables().size());
            assertEquals(0,
                         task.getTaskLocalVariables().size());
            assertTrue((Boolean) task.getProcessVariables().get("processVar"));

            taskService.setVariable(task.getId(),
                                    "anotherProcessVar",
                                    123);
            taskService.setVariableLocal(task.getId(),
                                         "localVar",
                                         "test");

            task = historyService.createHistoricTaskInstanceQuery().includeTaskLocalVariables().or().taskAssignee(KERMIT).taskVariableValueEquals("localVar",
                                                                                                                                                  "nonExisting").endOr().singleResult();
            assertEquals(0,
                         task.getProcessVariables().size());
            assertEquals(1,
                         task.getTaskLocalVariables().size());
            assertEquals("test",
                         task.getTaskLocalVariables().get("localVar"));

            task = historyService.createHistoricTaskInstanceQuery().includeProcessVariables().or().taskAssignee(KERMIT).taskVariableValueEquals("localVar",
                                                                                                                                                "nonExisting").endOr().singleResult();
            assertEquals(2,
                         task.getProcessVariables().size());
            assertEquals(0,
                         task.getTaskLocalVariables().size());
            assertEquals(true,
                         task.getProcessVariables().get("processVar"));
            assertEquals(123,
                         task.getProcessVariables().get("anotherProcessVar"));

            task = historyService.createHistoricTaskInstanceQuery()
                    .includeTaskLocalVariables()
                    .or()
                    .taskAssignee("nonexisting")
                    .taskVariableValueLike("testVar",
                                           "someVar%")
                    .endOr()
                    .singleResult();
            assertEquals(2,
                         task.getTaskLocalVariables().size());
            assertEquals(0,
                         task.getProcessVariables().size());
            assertEquals("someVariable",
                         task.getTaskLocalVariables().get("testVar"));
            assertEquals(123,
                         task.getTaskLocalVariables().get("testVar2"));

            task = historyService.createHistoricTaskInstanceQuery()
                    .includeTaskLocalVariables()
                    .or()
                    .taskAssignee("nonexisting")
                    .taskVariableValueLikeIgnoreCase("testVar",
                                                     "somevar%")
                    .endOr()
                    .singleResult();
            assertEquals(2,
                         task.getTaskLocalVariables().size());
            assertEquals(0,
                         task.getProcessVariables().size());
            assertEquals("someVariable",
                         task.getTaskLocalVariables().get("testVar"));
            assertEquals(123,
                         task.getTaskLocalVariables().get("testVar2"));

            task = historyService.createHistoricTaskInstanceQuery()
                    .includeTaskLocalVariables()
                    .or()
                    .taskAssignee("nonexisting")
                    .taskVariableValueLike("testVar",
                                           "someVar2%")
                    .endOr()
                    .singleResult();
            assertNull(task);

            tasks = historyService.createHistoricTaskInstanceQuery().includeTaskLocalVariables()
                    .or()
                    .taskInvolvedUser(KERMIT)
                    .taskVariableValueEquals("localVar",
                                             "nonExisting")
                    .endOr()
                    .orderByTaskCreateTime().asc().list();
            assertEquals(3,
                         tasks.size());
            assertEquals(1,
                         tasks.get(0).getTaskLocalVariables().size());
            assertEquals("test",
                         tasks.get(0).getTaskLocalVariables().get("test"));
            assertEquals(0,
                         tasks.get(0).getProcessVariables().size());

            tasks = historyService.createHistoricTaskInstanceQuery().includeProcessVariables()
                    .or()
                    .taskInvolvedUser(KERMIT)
                    .taskVariableValueEquals("localVar",
                                             "nonExisting")
                    .endOr()
                    .orderByTaskCreateTime().asc().list();
            assertEquals(3,
                         tasks.size());
            assertEquals(0,
                         tasks.get(0).getProcessVariables().size());
            assertEquals(0,
                         tasks.get(0).getTaskLocalVariables().size());

            task = historyService.createHistoricTaskInstanceQuery().includeTaskLocalVariables().taskAssignee(KERMIT).or().taskVariableValueEquals("localVar",
                                                                                                                                                  "test")
                    .taskVariableValueEquals("localVar",
                                             "nonExisting").endOr().singleResult();
            assertEquals(0,
                         task.getProcessVariables().size());
            assertEquals(1,
                         task.getTaskLocalVariables().size());
            assertEquals("test",
                         task.getTaskLocalVariables().get("localVar"));

            task = historyService.createHistoricTaskInstanceQuery().includeProcessVariables().taskAssignee(KERMIT).or().taskVariableValueEquals("localVar",
                                                                                                                                                "test")
                    .taskVariableValueEquals("localVar",
                                             "nonExisting").endOr().singleResult();
            assertEquals(2,
                         task.getProcessVariables().size());
            assertEquals(0,
                         task.getTaskLocalVariables().size());
            assertEquals(true,
                         task.getProcessVariables().get("processVar"));
            assertEquals(123,
                         task.getProcessVariables().get("anotherProcessVar"));

            task = historyService.createHistoricTaskInstanceQuery().includeTaskLocalVariables().includeProcessVariables().or().taskAssignee(KERMIT).taskVariableValueEquals("localVar",
                                                                                                                                                                            "nonExisting")
                    .endOr().singleResult();
            assertEquals(2,
                         task.getProcessVariables().size());
            assertEquals(1,
                         task.getTaskLocalVariables().size());
            assertEquals("test",
                         task.getTaskLocalVariables().get("localVar"));
            assertEquals(true,
                         task.getProcessVariables().get("processVar"));
            assertEquals(123,
                         task.getProcessVariables().get("anotherProcessVar"));

            task = historyService.createHistoricTaskInstanceQuery().taskAssignee(GONZO).singleResult();
            taskService.complete(task.getId());
            task = historyService.createHistoricTaskInstanceQuery().includeTaskLocalVariables().or().finished().taskVariableValueEquals("localVar",
                                                                                                                                        "nonExisting").endOr().singleResult();
            variableMap = task.getTaskLocalVariables();
            assertEquals(2,
                         variableMap.size());
            assertEquals(0,
                         task.getProcessVariables().size());
            assertNotNull(variableMap.get("testVar"));
            assertEquals("someVariable",
                         variableMap.get("testVar"));
            assertNotNull(variableMap.get("testVar2"));
            assertEquals(123,
                         variableMap.get("testVar2"));
        }
    }

    /**
     * Generates some test tasks. - 2 tasks where kermit is a candidate and 1 task where gonzo is assignee
     */
    private List<String> generateTestTasks() throws Exception {
        List<String> ids = new ArrayList<String>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
        // 2 tasks for kermit
        processEngineConfiguration.getClock().setCurrentTime(sdf.parse("01/01/2001 01:01:01.000"));
        for (int i = 0; i < 2; i++) {
            Task task = taskService.newTask();
            task.setName("testTask");
            task.setDescription("testTask description");
            task.setPriority(3);
            taskService.saveTask(task);
            ids.add(task.getId());
            taskService.setVariableLocal(task.getId(),
                                         "test",
                                         "test");
            taskService.addCandidateUser(task.getId(),
                                         KERMIT);
        }

        processEngineConfiguration.getClock().setCurrentTime(sdf.parse("02/02/2002 02:02:02.000"));
        // 1 task for gonzo
        Task task = taskService.newTask();
        task.setName("gonzoTask");
        task.setDescription("gonzo description");
        task.setPriority(4);
        task.setCategory("testCategory");
        taskService.saveTask(task);
        taskService.setAssignee(task.getId(),
                                GONZO);
        taskService.setVariableLocal(task.getId(),
                                     "testVar",
                                     "someVariable");
        taskService.setVariableLocal(task.getId(),
                                     "testVar2",
                                     123);
        ids.add(task.getId());

        return ids;
    }
}
