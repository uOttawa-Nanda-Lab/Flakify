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
package org.activiti.engine.test.api.event;

import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.impl.ActivitiEventBuilder;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TimerJobEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.test.Deployment;

/**
 * Test case for all {@link ActivitiEvent}s related to process definitions.
 * 

 */
public class ProcessDefinitionEventsTest extends PluggableActivitiTestCase {

  private TestMultipleActivitiEventListener listener;

  @Deployment(resources={"org/activiti/engine/test/bpmn/event/timer/StartTimerEventTest.testDurationStartTimerEvent.bpmn20.xml"}) public void testTimerStartEventDeployment(){ProcessDefinitionEntity processDefinition=(ProcessDefinitionEntity)repositoryService.createProcessDefinitionQuery().processDefinitionKey("startTimerEventExample").singleResult();ActivitiEntityEvent processDefinitionCreated=ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_CREATED,processDefinition);TimerJobEntity timer=(TimerJobEntity)managementService.createTimerJobQuery().singleResult();ActivitiEntityEvent timerCreated=ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_CREATED,timer);assertSequence(processDefinitionCreated,timerCreated);listener.clearEventsReceived();}

  protected void assertSequence(ActivitiEntityEvent before, ActivitiEntityEvent after) {
    int beforeIndex = 0;
    int afterIndex = 0;
    for (int index = 0; index < listener.getEventsReceived().size(); index++) {
      ActivitiEvent activitiEvent = listener.getEventsReceived().get(index);

      if (isEqual(before, activitiEvent))
        beforeIndex = index;
      if (isEqual(after, activitiEvent))
        afterIndex = index;
    }
    assertTrue(beforeIndex < afterIndex);
  }

  /**
   * equals is not implemented.
   */
  private boolean isEqual(ActivitiEntityEvent event1, ActivitiEvent activitiEvent) {
    if (activitiEvent instanceof ActivitiEntityEvent && event1.getType().equals(activitiEvent.getType())) {
      ActivitiEntityEvent activitiEntityEvent = (ActivitiEntityEvent) activitiEvent;
      if (activitiEntityEvent.getEntity().getClass().equals(event1.getEntity().getClass())) {
        return true;
      }
    }
    return false;
  }
}
