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

package org.activiti.examples.bpmn.callactivity;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.engine.test.Deployment;

/**

 */
public class CallActivityTest extends PluggableActivitiTestCase {

  @Deployment(resources={"org/activiti/examples/bpmn/callactivity/mainProcess.bpmn20.xml","org/activiti/examples/bpmn/callactivity/childProcess.bpmn20.xml"}) public void testCallActivityWithModeledDataObjectsInSubProcess(){ProcessInstance pi=runtimeService.startProcessInstanceByKey("mainProcess");TaskQuery taskQuery=taskService.createTaskQuery();Task verifyCreditTask=taskQuery.singleResult();assertEquals("User Task 1",verifyCreditTask.getName());ProcessInstance subProcessInstance=runtimeService.createProcessInstanceQuery().superProcessInstanceId(pi.getId()).singleResult();assertNotNull(subProcessInstance);assertEquals(pi.getId(),runtimeService.createProcessInstanceQuery().subProcessInstanceId(subProcessInstance.getId()).singleResult().getId());assertEquals("Batman",runtimeService.getVariable(subProcessInstance.getId(),"Name"));}
}
