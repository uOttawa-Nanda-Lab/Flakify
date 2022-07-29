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
package org.activiti.standalone.testing;

import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.activiti.engine.test.mock.ActivitiMockSupport;
import org.activiti.engine.test.mock.MockServiceTask;
import org.activiti.engine.test.mock.MockServiceTasks;
import org.activiti.engine.test.mock.NoOpServiceTasks;
import org.activiti.standalone.testing.helpers.ServiceTaskTestMock;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

/**

 */
public class MockSupportWithActivitiRuleTest {

  @Rule
  public ActivitiRule activitiRule = new ActivitiRule() {

    protected void configureProcessEngine() {
      ServiceTaskTestMock.CALL_COUNT.set(0);

      activitiRule.mockSupport().mockServiceTaskWithClassDelegate("com.yourcompany.delegate", ServiceTaskTestMock.class);
      activitiRule.mockSupport().mockServiceTaskWithClassDelegate("com.yourcompany.anotherDelegate", "org.activiti.standalone.testing.helpers.ServiceTaskTestMock");
    }

  };

  @Test @Deployment(resources={"org/activiti/standalone/testing/MockSupportWithActivitiRuleTest.testNoOpServiceTasksAnnotation.bpmn20.xml"}) @NoOpServiceTasks(ids={"serviceTask1","serviceTask3","serviceTask5"}, classNames={"com.yourcompany.delegate2","com.yourcompany.delegate4"}) public void testNoOpServiceTasksWithIdsAnnotation(){ActivitiMockSupport mockSupport=activitiRule.getMockSupport();Assert.assertEquals(0,mockSupport.getNrOfNoOpServiceTaskExecutions());activitiRule.getRuntimeService().startProcessInstanceByKey("mockSupportTest");Assert.assertEquals(5,mockSupport.getNrOfNoOpServiceTaskExecutions());for (int i=1;i <= 5;i++){Assert.assertEquals("com.yourcompany.delegate" + i,mockSupport.getExecutedNoOpServiceTaskDelegateClassNames().get(i - 1));}}

}
