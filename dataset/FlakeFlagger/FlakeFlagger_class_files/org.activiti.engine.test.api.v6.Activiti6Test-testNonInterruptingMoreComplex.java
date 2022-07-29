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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

/**
 * These are the first tests ever written for Activiti 6.
 * Keeping them here for nostalgic reasons.
 */
public class Activiti6Test extends PluggableActivitiTestCase {

    @Test @org.activiti.engine.test.Deployment public void testNonInterruptingMoreComplex(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("nonInterruptingTimer");assertNotNull(processInstance);assertFalse(processInstance.isEnded());List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();assertEquals(2,tasks.size());assertEquals("A",tasks.get(0).getName());assertEquals("B",tasks.get(1).getName());List<Job> jobs=managementService.createTimerJobQuery().list();assertEquals(2,jobs.size());for (Job job:jobs){managementService.moveTimerToExecutableJob(job.getId());managementService.executeJob(job.getId());}tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();assertEquals(5,tasks.size());assertEquals("A",tasks.get(0).getName());assertEquals("C",tasks.get(1).getName());assertEquals("D",tasks.get(2).getName());assertEquals("E",tasks.get(3).getName());assertEquals("F",tasks.get(4).getName());jobs=managementService.createTimerJobQuery().list();assertEquals(1,jobs.size());managementService.moveTimerToExecutableJob(jobs.get(0).getId());managementService.executeJob(jobs.get(0).getId());tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();assertEquals(6,tasks.size());assertEquals("A",tasks.get(0).getName());assertEquals("C",tasks.get(1).getName());assertEquals("D",tasks.get(2).getName());assertEquals("E",tasks.get(3).getName());assertEquals("F",tasks.get(4).getName());assertEquals("G",tasks.get(5).getName());for (Task task:tasks){taskService.complete(task.getId());}assertEquals(0,runtimeService.createExecutionQuery().count());}
}
