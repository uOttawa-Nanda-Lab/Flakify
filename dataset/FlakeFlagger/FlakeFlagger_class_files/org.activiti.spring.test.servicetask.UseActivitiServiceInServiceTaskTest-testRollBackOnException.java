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
package org.activiti.spring.test.servicetask;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.Deployment;
import org.activiti.spring.impl.test.SpringActivitiTestCase;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

/**

 */
@ContextConfiguration("classpath:org/activiti/spring/test/servicetask/servicetaskSpringTest-context.xml")
public class UseActivitiServiceInServiceTaskTest extends SpringActivitiTestCase {

  @Deployment
  public void testRollBackOnException() {
    Exception expectedException = null;
    try {
      runtimeService.startProcessInstanceByKey("startProcessFromDelegate");
    } catch (Exception e) {
      expectedException = e;
    }
    assertNotNull(expectedException);

    // Starting the process should cause a rollback of both processes
    assertEquals(0, runtimeService.createProcessInstanceQuery().count());
  }

}
