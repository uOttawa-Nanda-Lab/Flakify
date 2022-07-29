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
package org.activiti.examples.processdefinitions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.activiti.engine.impl.bpmn.deployer.ResourceNameUtil;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.repository.ProcessDefinition;

/**


 */
public class ProcessDefinitionsTest extends PluggableActivitiTestCase {

  private static final String NAMESPACE = "xmlns='http://www.omg.org/spec/BPMN/20100524/MODEL'";

  private static final String TARGET_NAMESPACE = "targetNamespace='http://activiti.org/BPMN20'";

  public void testDeployIdenticalProcessDefinitions(){List<String> deploymentIds=new ArrayList<String>();deploymentIds.add(deployProcessString(("<definitions " + NAMESPACE + " " + TARGET_NAMESPACE + ">" + "  <process id='IDR' name='Insurance Damage Report' />" + "</definitions>")));deploymentIds.add(deployProcessString(("<definitions " + NAMESPACE + " " + TARGET_NAMESPACE + ">" + "  <process id='IDR' name='Insurance Damage Report' />" + "</definitions>")));List<ProcessDefinition> processDefinitions=repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionKey().asc().orderByProcessDefinitionVersion().desc().list();assertNotNull(processDefinitions);assertEquals(2,processDefinitions.size());ProcessDefinition processDefinition=processDefinitions.get(0);assertEquals("IDR",processDefinition.getKey());assertEquals("Insurance Damage Report",processDefinition.getName());assertTrue(processDefinition.getId().startsWith("IDR:2"));assertEquals(2,processDefinition.getVersion());processDefinition=processDefinitions.get(1);assertEquals("IDR",processDefinition.getKey());assertEquals("Insurance Damage Report",processDefinition.getName());assertTrue(processDefinition.getId().startsWith("IDR:1"));assertEquals(1,processDefinition.getVersion());deleteDeployments(deploymentIds);}

  private String deployProcessString(String processString) {
    String resourceName = "xmlString." + ResourceNameUtil.BPMN_RESOURCE_SUFFIXES[0];
    return repositoryService.createDeployment().addString(resourceName, processString).deploy().getId();
  }

  private void deleteDeployments(Collection<String> deploymentIds) {
    for (String deploymentId : deploymentIds) {
      repositoryService.deleteDeployment(deploymentId);
    }
  }
}
