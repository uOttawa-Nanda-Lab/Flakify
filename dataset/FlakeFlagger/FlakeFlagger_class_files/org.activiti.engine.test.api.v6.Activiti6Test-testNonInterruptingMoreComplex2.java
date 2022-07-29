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

    @Test @org.activiti.engine.test.Deployment public void testNonInterruptingMoreComplex2(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("nonInterruptingWithInclusiveMerge");assertNotNull(processInstance);assertFalse(processInstance.isEnded());List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();assertEquals(2,tasks.size());assertEquals("A",tasks.get(0).getName());assertEquals("B",tasks.get(1).getName());assertEquals(2,managementService.createTimerJobQuery().count());taskService.complete(tasks.get(0).getId());tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();assertEquals(1,tasks.size());assertEquals("B",tasks.get(0).getName());assertEquals(1,managementService.createTimerJobQuery().count());taskService.complete(tasks.get(0).getId());assertEquals(0,managementService.createTimerJobQuery().count());assertEquals(0,runtimeService.createExecutionQuery().count());processInstance=runtimeService.startProcessInstanceByKey("nonInterruptingWithInclusiveMerge");tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();assertEquals(2,tasks.size());assertEquals("A",tasks.get(0).getName());assertEquals("B",tasks.get(1).getName());assertEquals(2,managementService.createTimerJobQuery().count());taskService.complete(tasks.get(1).getId());tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();assertEquals(1,tasks.size());assertEquals("A",tasks.get(0).getName());assertEquals(1,managementService.createTimerJobQuery().count());String jobId=managementService.createTimerJobQuery().singleResult().getId();managementService.moveTimerToExecutableJob(jobId);managementService.executeJob(jobId);tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();assertEquals(3,tasks.size());assertEquals("A",tasks.get(0).getName());assertEquals("C",tasks.get(1).getName());assertEquals("D",tasks.get(2).getName());assertEquals(1,managementService.createTimerJobQuery().count());jobId=managementService.createTimerJobQuery().singleResult().getId();managementService.moveTimerToExecutableJob(jobId);managementService.executeJob(jobId);tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();assertEquals(4,tasks.size());assertEquals("A",tasks.get(0).getName());assertEquals("C",tasks.get(1).getName());assertEquals("D",tasks.get(2).getName());assertEquals("G",tasks.get(3).getName());taskService.complete(taskService.createTaskQuery().taskName("C").singleResult().getId());tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();assertEquals(3,tasks.size());assertEquals("A",tasks.get(0).getName());assertEquals("D",tasks.get(1).getName());assertEquals("G",tasks.get(2).getName());taskService.complete(taskService.createTaskQuery().taskName("D").singleResult().getId());tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();assertEquals(2,tasks.size());assertEquals("A",tasks.get(0).getName());assertEquals("G",tasks.get(1).getName());taskService.complete(taskService.createTaskQuery().taskName("A").singleResult().getId());tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();assertEquals(1,tasks.size());assertEquals("G",tasks.get(0).getName());taskService.complete(taskService.createTaskQuery().taskName("G").singleResult().getId());assertEquals(0,runtimeService.createExecutionQuery().count());}
}
