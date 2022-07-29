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

package org.activiti.engine.test.api.repository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.ParallelGateway;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.test.Deployment;

/**


 */
public class RepositoryServiceTest extends PluggableActivitiTestCase {

  @Deployment public void testGetBpmnModel(){ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();BpmnModel bpmnModel=repositoryService.getBpmnModel(processDefinition.getId());assertNotNull(bpmnModel);assertEquals(1,bpmnModel.getProcesses().size());assertTrue(!bpmnModel.getLocationMap().isEmpty());assertTrue(!bpmnModel.getFlowLocationMap().isEmpty());org.activiti.bpmn.model.Process process=bpmnModel.getProcesses().get(0);List<StartEvent> startEvents=process.findFlowElementsOfType(StartEvent.class);assertEquals(1,startEvents.size());StartEvent startEvent=startEvents.get(0);assertEquals(1,startEvent.getOutgoingFlows().size());assertEquals(0,startEvent.getIncomingFlows().size());String nextElementId=startEvent.getOutgoingFlows().get(0).getTargetRef();UserTask userTask=(UserTask)process.getFlowElement(nextElementId);assertEquals("First Task",userTask.getName());assertEquals(1,userTask.getOutgoingFlows().size());assertEquals(1,userTask.getIncomingFlows().size());nextElementId=userTask.getOutgoingFlows().get(0).getTargetRef();ParallelGateway parallelGateway=(ParallelGateway)process.getFlowElement(nextElementId);assertEquals(2,parallelGateway.getOutgoingFlows().size());nextElementId=parallelGateway.getOutgoingFlows().get(0).getTargetRef();assertEquals(1,parallelGateway.getIncomingFlows().size());userTask=(UserTask)process.getFlowElement(nextElementId);assertEquals(1,userTask.getOutgoingFlows().size());nextElementId=userTask.getOutgoingFlows().get(0).getTargetRef();parallelGateway=(ParallelGateway)process.getFlowElement(nextElementId);assertEquals(1,parallelGateway.getOutgoingFlows().size());assertEquals(2,parallelGateway.getIncomingFlows().size());nextElementId=parallelGateway.getOutgoingFlows().get(0).getTargetRef();EndEvent endEvent=(EndEvent)process.getFlowElement(nextElementId);assertEquals(0,endEvent.getOutgoingFlows().size());assertEquals(1,endEvent.getIncomingFlows().size());}

}
