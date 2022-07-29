/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package org.activiti.spring.test.expression.callactivity;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.engine.test.Deployment;
import org.activiti.spring.impl.test.SpringActivitiTestCase;
import org.springframework.test.context.ContextConfiguration;

/**
 * The CallActivityBasedOnSpringBeansExpressionTest is isUsed to test dynamically wiring in the calledElement in the callActivity task. This test case helps verify that we do not have to hard code the
 * sub process definition key within the process.
 * 

 */
@ContextConfiguration("classpath:org/activiti/spring/test/expression/callactivity/testCallActivityByExpression-context.xml")
public class CallActivityBasedOnSpringBeansExpressionTest extends SpringActivitiTestCase {

  @Deployment(resources={"org/activiti/spring/test/expression/callactivity/CallActivityBasedOnSpringBeansExpressionTest.testCallActivityByExpression.bpmn20.xml","org/activiti/spring/test/expression/callactivity/simpleSubProcess.bpmn20.xml"}) public void testCallActivityByExpression() throws Exception{ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testCallActivityByExpression");TaskQuery taskQuery=taskService.createTaskQuery();Task taskBeforeSubProcess=taskQuery.singleResult();assertEquals("Task before subprocess",taskBeforeSubProcess.getName());taskService.complete(taskBeforeSubProcess.getId());Task taskInSubProcess=taskQuery.singleResult();assertEquals("Task in subprocess",taskInSubProcess.getName());taskService.complete(taskInSubProcess.getId());Task taskAfterSubProcess=taskQuery.singleResult();assertEquals("Task after subprocess",taskAfterSubProcess.getName());taskService.complete(taskAfterSubProcess.getId());assertProcessEnded(processInstance.getId());}

}
