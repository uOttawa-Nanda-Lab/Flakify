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

    public void testQueryWithPagingAndVariables(){if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)){List<HistoricTaskInstance> tasks=historyService.createHistoricTaskInstanceQuery().includeProcessVariables().includeTaskLocalVariables().orderByTaskPriority().desc().listPage(0,1);assertEquals(1,tasks.size());HistoricTaskInstance task=tasks.get(0);Map<String, Object> variableMap=task.getTaskLocalVariables();assertEquals(2,variableMap.size());assertEquals("someVariable",variableMap.get("testVar"));assertEquals(123,variableMap.get("testVar2"));tasks=historyService.createHistoricTaskInstanceQuery().includeProcessVariables().includeTaskLocalVariables().orderByTaskPriority().asc().listPage(1,2);assertEquals(2,tasks.size());task=tasks.get(1);variableMap=task.getTaskLocalVariables();assertEquals(2,variableMap.size());assertEquals("someVariable",variableMap.get("testVar"));assertEquals(123,variableMap.get("testVar2"));tasks=historyService.createHistoricTaskInstanceQuery().includeProcessVariables().includeTaskLocalVariables().orderByTaskPriority().asc().listPage(2,4);assertEquals(1,tasks.size());task=tasks.get(0);variableMap=task.getTaskLocalVariables();assertEquals(2,variableMap.size());assertEquals("someVariable",variableMap.get("testVar"));assertEquals(123,variableMap.get("testVar2"));tasks=historyService.createHistoricTaskInstanceQuery().includeProcessVariables().includeTaskLocalVariables().orderByTaskPriority().asc().listPage(4,2);assertEquals(0,tasks.size());}}

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
