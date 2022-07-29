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

package org.activiti.engine.test.api.runtime;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.history.DeleteReason;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.HistoricDetailVariableInstanceUpdateEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceBuilder;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

import static org.assertj.core.api.Assertions.*;

public class RuntimeServiceTest extends PluggableActivitiTestCase {

    private void checkHistoricVariableUpdateEntity(String variableName,
                                                   String processInstanceId) {
        if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.FULL)) {
            boolean deletedVariableUpdateFound = false;

            List<HistoricDetail> resultSet = historyService.createHistoricDetailQuery().processInstanceId(processInstanceId).list();
            for (HistoricDetail currentHistoricDetail : resultSet) {
                assertTrue(currentHistoricDetail instanceof HistoricDetailVariableInstanceUpdateEntity);
                HistoricDetailVariableInstanceUpdateEntity historicVariableUpdate = (HistoricDetailVariableInstanceUpdateEntity) currentHistoricDetail;

                if (historicVariableUpdate.getName().equals(variableName)) {
                    if (historicVariableUpdate.getValue() == null) {
                        if (deletedVariableUpdateFound) {
                            fail("Mismatch: A HistoricVariableUpdateEntity with a null value already found");
                        } else {
                            deletedVariableUpdateFound = true;
                        }
                    }
                }
            }

            assertTrue(deletedVariableUpdateFound);
        }
    }

    @Deployment(resources={"org/activiti/engine/test/api/oneSubProcess.bpmn20.xml"}) public void testRemoveVariablesLocalWithParentScope(){Map<String, Object> vars=new HashMap<String, Object>();vars.put("variable1","value1");vars.put("variable2","value2");ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("startSimpleSubProcess",vars);Task currentTask=taskService.createTaskQuery().singleResult();Map<String, Object> varsToDelete=new HashMap<String, Object>();varsToDelete.put("variable3","value3");varsToDelete.put("variable4","value4");varsToDelete.put("variable5","value5");runtimeService.setVariablesLocal(currentTask.getExecutionId(),varsToDelete);runtimeService.setVariableLocal(currentTask.getExecutionId(),"variable6","value6");assertEquals("value3",runtimeService.getVariable(currentTask.getExecutionId(),"variable3"));assertEquals("value3",runtimeService.getVariableLocal(currentTask.getExecutionId(),"variable3"));assertEquals("value4",runtimeService.getVariable(currentTask.getExecutionId(),"variable4"));assertEquals("value4",runtimeService.getVariableLocal(currentTask.getExecutionId(),"variable4"));assertEquals("value5",runtimeService.getVariable(currentTask.getExecutionId(),"variable5"));assertEquals("value5",runtimeService.getVariableLocal(currentTask.getExecutionId(),"variable5"));assertEquals("value6",runtimeService.getVariable(currentTask.getExecutionId(),"variable6"));assertEquals("value6",runtimeService.getVariableLocal(currentTask.getExecutionId(),"variable6"));runtimeService.removeVariablesLocal(currentTask.getExecutionId(),varsToDelete.keySet());assertEquals("value1",runtimeService.getVariable(currentTask.getExecutionId(),"variable1"));assertEquals("value2",runtimeService.getVariable(currentTask.getExecutionId(),"variable2"));assertNull(runtimeService.getVariable(currentTask.getExecutionId(),"variable3"));assertNull(runtimeService.getVariableLocal(currentTask.getExecutionId(),"variable3"));assertNull(runtimeService.getVariable(currentTask.getExecutionId(),"variable4"));assertNull(runtimeService.getVariableLocal(currentTask.getExecutionId(),"variable4"));assertNull(runtimeService.getVariable(currentTask.getExecutionId(),"variable5"));assertNull(runtimeService.getVariableLocal(currentTask.getExecutionId(),"variable5"));assertEquals("value6",runtimeService.getVariable(currentTask.getExecutionId(),"variable6"));assertEquals("value6",runtimeService.getVariableLocal(currentTask.getExecutionId(),"variable6"));checkHistoricVariableUpdateEntity("variable3",processInstance.getId());checkHistoricVariableUpdateEntity("variable4",processInstance.getId());checkHistoricVariableUpdateEntity("variable5",processInstance.getId());}

    private void startSignalCatchProcesses() {
        for (int i = 0; i < 3; i++) {
            runtimeService.startProcessInstanceByKey("catchAlertSignal");
            runtimeService.startProcessInstanceByKey("catchPanicSignal");
        }
    }

    private void startMessageCatchProcesses() {
        for (int i = 0; i < 3; i++) {
            runtimeService.startProcessInstanceByKey("catchAlertMessage");
            runtimeService.startProcessInstanceByKey("catchPanicMessage");
        }
    }
}
