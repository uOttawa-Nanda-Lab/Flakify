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
package org.activiti.examples.bpmn.servicetask;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ActivitiClassLoadingException;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**


 */
public class JavaServiceTaskTest extends PluggableActivitiTestCase {

  @Deployment public void testExpressionFieldInjection(){Map<String, Object> vars=new HashMap<String, Object>();vars.put("name","kermit");vars.put("gender","male");vars.put("genderBean",new GenderBean());ProcessInstance pi=runtimeService.startProcessInstanceByKey("expressionFieldInjection",vars);Execution execution=runtimeService.createExecutionQuery().processInstanceId(pi.getId()).activityId("waitState").singleResult();assertEquals("timrek .rM olleH",runtimeService.getVariable(execution.getId(),"var2"));assertEquals("elam :si redneg ruoY",runtimeService.getVariable(execution.getId(),"var1"));}

}
