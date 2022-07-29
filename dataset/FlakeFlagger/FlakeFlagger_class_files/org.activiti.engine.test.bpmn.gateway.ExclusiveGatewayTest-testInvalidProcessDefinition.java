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
package org.activiti.engine.test.bpmn.gateway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**

 */
public class ExclusiveGatewayTest extends PluggableActivitiTestCase {

  public void testInvalidProcessDefinition() {
	String defaultFlowWithCondition = "<?xml version='1.0' encoding='UTF-8'?>"
			+ "<definitions id='definitions' xmlns='http://www.omg.org/spec/BPMN/20100524/MODEL' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:activiti='http://activiti.org/bpmn' targetNamespace='Examples'>"
			+ "  <process id='exclusiveGwDefaultSequenceFlow'> " + "    <startEvent id='theStart' /> "
			+ "    <sequenceFlow id='flow1' sourceRef='theStart' targetRef='exclusiveGw' /> "
			+ "    <exclusiveGateway id='exclusiveGw' name='Exclusive Gateway' default='flow3' /> "
			+ "    <sequenceFlow id='flow2' sourceRef='exclusiveGw' targetRef='theTask1'> "
			+ "      <conditionExpression xsi:type='tFormalExpression'>${input == 1}</conditionExpression> "
			+ "    </sequenceFlow> " + "    <sequenceFlow id='flow3' sourceRef='exclusiveGw' targetRef='theTask2'> "
			+ "      <conditionExpression xsi:type='tFormalExpression'>${input == 3}</conditionExpression> "
			+ "    </sequenceFlow> " + "    <userTask id='theTask1' name='Input is one' /> "
			+ "    <userTask id='theTask2' name='Default input' /> " + "  </process>" + "</definitions>";
	try {
		repositoryService.createDeployment().addString("myprocess.bpmn20.xml", defaultFlowWithCondition).deploy();
		fail();
	} catch (Exception e) {
	}
	String noOutgoingFlow = "<?xml version='1.0' encoding='UTF-8'?>"
			+ "<definitions id='definitions' xmlns='http://www.omg.org/spec/BPMN/20100524/MODEL' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:activiti='http://activiti.org/bpmn' targetNamespace='Examples'>"
			+ "  <process id='exclusiveGwDefaultSequenceFlow'> " + "    <startEvent id='theStart' /> "
			+ "    <sequenceFlow id='flow1' sourceRef='theStart' targetRef='exclusiveGw' /> "
			+ "    <exclusiveGateway id='exclusiveGw' name='Exclusive Gateway' /> " + "  </process>" + "</definitions>";
	try {
		repositoryService.createDeployment().addString("myprocess.bpmn20.xml", noOutgoingFlow).deploy();
		fail("Could deploy a process definition with a XOR Gateway without outgoing sequence flows.");
	} catch (ActivitiException ex) {
	}
}

}
