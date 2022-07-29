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
package org.activiti.engine.test.bpmn.event.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.TimerJobQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

public class IntermediateTimerEventTest extends PluggableActivitiTestCase {

  @Deployment public void testCatchingTimerEvent() throws Exception{Date startTime=new Date();ProcessInstance pi=runtimeService.startProcessInstanceByKey("intermediateTimerEventExample");TimerJobQuery jobQuery=managementService.createTimerJobQuery().processInstanceId(pi.getId());assertEquals(1,jobQuery.count());processEngineConfiguration.getClock().setCurrentTime(new Date(startTime.getTime() + ((50 * 60 * 1000) + 5000)));waitForJobExecutorToProcessAllJobs(5000L,25L);assertEquals(0,jobQuery.count());assertProcessEnded(pi.getProcessInstanceId());}

}
