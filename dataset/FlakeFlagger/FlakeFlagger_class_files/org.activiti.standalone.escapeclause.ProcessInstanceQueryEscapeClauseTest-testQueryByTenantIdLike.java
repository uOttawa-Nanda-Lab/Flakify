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
package org.activiti.standalone.escapeclause;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

public class ProcessInstanceQueryEscapeClauseTest extends AbstractEscapeClauseTestCase {

  private String deploymentOneId;

  private String deploymentTwoId;

  private ProcessInstance processInstance1;
  
  private ProcessInstance processInstance2;
  
  public void testQueryByTenantIdLike() {
    // tenantIdLike
    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceTenantIdLike("%\\%%").singleResult();
    assertNotNull(processInstance);
    assertEquals(processInstance1.getId(), processInstance.getId());
    
    processInstance = runtimeService.createProcessInstanceQuery().processInstanceTenantIdLike("%\\_%").singleResult();
    assertNotNull(processInstance);
    assertEquals(processInstance2.getId(), processInstance.getId());
    
    // orQuery
    processInstance = runtimeService.createProcessInstanceQuery().or().processInstanceTenantIdLike("%\\%%").processDefinitionId("undefined").singleResult();
    assertNotNull(processInstance);
    assertEquals(processInstance1.getId(), processInstance.getId());
    
    processInstance = runtimeService.createProcessInstanceQuery().or().processInstanceTenantIdLike("%\\_%").processDefinitionId("undefined").singleResult();
    assertNotNull(processInstance);
    assertEquals(processInstance2.getId(), processInstance.getId());
  }
}
