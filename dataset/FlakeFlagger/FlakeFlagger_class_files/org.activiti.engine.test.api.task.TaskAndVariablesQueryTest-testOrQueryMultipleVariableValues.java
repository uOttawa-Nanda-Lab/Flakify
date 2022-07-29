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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.engine.test.Deployment;

/**

 */
public class TaskAndVariablesQueryTest extends PluggableActivitiTestCase {

    private List<String> taskIds;
    private List<String> multipleTaskIds;

    private static final String KERMIT = "kermit";
    private static final List<String> KERMITSGROUPS = Arrays.asList("management",
                                                                    "accountancy");

    private static final String GONZO = "gonzo";

    @Deployment
    public void testOrQueryMultipleVariableValues() {
        Map<String, Object> startMap = new HashMap<String, Object>();
        startMap.put("aProcessVar",
                     1);
        startMap.put("anotherProcessVar",
                     123);
        runtimeService.startProcessInstanceByKey("oneTaskProcess",
                                                 startMap);

        TaskQuery query0 = taskService.createTaskQuery().includeProcessVariables().or();
        for (int i = 0; i < 20; i++) {
            query0 = query0.processVariableValueEquals("anotherProcessVar",
                                                       i);
        }
        query0 = query0.endOr();
        assertNull(query0.singleResult());

        TaskQuery query1 = taskService.createTaskQuery().includeProcessVariables().or().processVariableValueEquals("anotherProcessVar",
                                                                                                                   123);
        for (int i = 0; i < 20; i++) {
            query1 = query1.processVariableValueEquals("anotherProcessVar",
                                                       i);
        }
        query1 = query1.endOr();
        Task task = query1.singleResult();
        assertEquals(2,
                     task.getProcessVariables().size());
        assertEquals(123,
                     task.getProcessVariables().get("anotherProcessVar"));
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
            taskService.setVariableLocal(task.getId(),
                                         "testBinary",
                                         "This is a binary variable".getBytes());
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
                                     "testVarBinary",
                                     "This is a binary variable".getBytes());
        taskService.setVariableLocal(task.getId(),
                                     "testVar2",
                                     123);
        ids.add(task.getId());

        return ids;
    }

    /**
     * Generates 100 test tasks.
     */
    private List<String> generateMultipleTestTasks() throws Exception {
        List<String> ids = new ArrayList<String>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
        processEngineConfiguration.getClock().setCurrentTime(sdf.parse("01/01/2001 01:01:01.000"));
        for (int i = 0; i < 100; i++) {
            Task task = taskService.newTask();
            task.setName("testTask");
            task.setDescription("testTask description");
            task.setPriority(3);
            taskService.saveTask(task);
            ids.add(task.getId());
            taskService.setVariableLocal(task.getId(),
                                         "test",
                                         "test");
            taskService.setVariableLocal(task.getId(),
                                         "testBinary",
                                         "This is a binary variable".getBytes());
            taskService.addCandidateUser(task.getId(),
                                         KERMIT);
        }
        return ids;
    }
}
