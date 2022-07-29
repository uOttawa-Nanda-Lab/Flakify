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
package org.activiti.engine.test.jobexecutor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.activiti.engine.impl.asyncexecutor.FindExpiredJobsCmd;
import org.activiti.engine.impl.asyncexecutor.ResetExpiredJobsCmd;
import org.activiti.engine.impl.cmd.AcquireJobsCmd;
import org.activiti.engine.impl.persistence.entity.JobEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.JobQuery;
import org.activiti.engine.test.Deployment;

/**

 */
public class ResetExpiredJobsTest extends PluggableActivitiTestCase {
  
  @Deployment public void testResetExpiredJobs(){Date startOfTestTime=new Date();processEngineConfiguration.getClock().setCurrentTime(startOfTestTime);runtimeService.startProcessInstanceByKey("myProcess");assertEquals(1,managementService.createJobQuery().count());int expiredJobsPagesSize=processEngineConfiguration.getAsyncExecutorResetExpiredJobsPageSize();List<JobEntity> expiredJobs=managementService.executeCommand(new FindExpiredJobsCmd(expiredJobsPagesSize));assertEquals(0,expiredJobs.size());assertJobDetails(false);managementService.executeCommand(new AcquireJobsCmd(processEngineConfiguration.getAsyncExecutor()));assertJobDetails(true);expiredJobs=managementService.executeCommand(new FindExpiredJobsCmd(expiredJobsPagesSize));assertEquals(0,expiredJobs.size());assertJobDetails(true);Date newDate=new Date(startOfTestTime.getTime() + processEngineConfiguration.getAsyncExecutor().getAsyncJobLockTimeInMillis() + 10000);processEngineConfiguration.getClock().setCurrentTime(newDate);expiredJobs=managementService.executeCommand(new FindExpiredJobsCmd(expiredJobsPagesSize));assertTrue(expiredJobs.size() > 0);List<String> jobIds=new ArrayList<String>();for (JobEntity jobEntity:expiredJobs){jobIds.add(jobEntity.getId());}managementService.executeCommand(new ResetExpiredJobsCmd(jobIds));assertJobDetails(false);managementService.executeCommand(new AcquireJobsCmd(processEngineConfiguration.getAsyncExecutor()));assertJobDetails(true);runtimeService.startProcessInstanceByKey("myProcess");runtimeService.startProcessInstanceByKey("myProcess");assertEquals(3,managementService.createJobQuery().count());assertJobDetails(true);List<Job> unlockedJobs=managementService.createJobQuery().unlocked().list();assertEquals(2,unlockedJobs.size());for (Job job:unlockedJobs){JobEntity jobEntity=(JobEntity)job;assertNull(jobEntity.getLockOwner());assertNull(jobEntity.getLockExpirationTime());}}

  protected void assertJobDetails(boolean locked) {
    JobQuery jobQuery = managementService.createJobQuery();
    
    if (locked) {
      jobQuery.locked(); 
    }
    
    Job job = jobQuery.singleResult();
    assertTrue(job instanceof JobEntity);
    JobEntity jobEntity = (JobEntity) job;
    
    if (locked) {
      assertNotNull(jobEntity.getLockOwner());
      assertNotNull(jobEntity.getLockExpirationTime());
    } else {
      assertNull(jobEntity.getLockOwner());
      assertNull(jobEntity.getLockExpirationTime());
    }
  }
  

}
