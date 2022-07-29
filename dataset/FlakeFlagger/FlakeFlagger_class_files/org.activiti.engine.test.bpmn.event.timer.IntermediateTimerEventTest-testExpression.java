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

  @Deployment public void testExpression(){HashMap<String, Object> variables1=new HashMap<String, Object>();variables1.put("dueDate",new Date());HashMap<String, Object> variables2=new HashMap<String, Object>();variables2.put("dueDate",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));ProcessInstance pi1=runtimeService.startProcessInstanceByKey("intermediateTimerEventExample",variables1);ProcessInstance pi2=runtimeService.startProcessInstanceByKey("intermediateTimerEventExample",variables2);assertEquals(1,managementService.createTimerJobQuery().processInstanceId(pi1.getId()).count());assertEquals(1,managementService.createTimerJobQuery().processInstanceId(pi2.getId()).count());List<Job> jobs=managementService.createTimerJobQuery().executable().list();assertEquals(2,jobs.size());for (Job job:jobs){managementService.moveTimerToExecutableJob(job.getId());managementService.executeJob(job.getId());}assertEquals(0,managementService.createTimerJobQuery().processInstanceId(pi1.getId()).count());assertEquals(0,managementService.createTimerJobQuery().processInstanceId(pi2.getId()).count());assertProcessEnded(pi1.getProcessInstanceId());assertProcessEnded(pi2.getProcessInstanceId());}

}
