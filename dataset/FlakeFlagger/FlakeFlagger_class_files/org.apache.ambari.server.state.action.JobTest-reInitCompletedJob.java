/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ambari.server.state.action;

import org.apache.ambari.server.state.action.Action;
import org.apache.ambari.server.state.action.ActionCompletedEvent;
import org.apache.ambari.server.state.action.ActionEvent;
import org.apache.ambari.server.state.action.ActionFailedEvent;
import org.apache.ambari.server.state.action.ActionId;
import org.apache.ambari.server.state.action.ActionImpl;
import org.apache.ambari.server.state.action.ActionProgressUpdateEvent;
import org.apache.ambari.server.state.action.ActionState;
import org.apache.ambari.server.state.action.ActionType;
import org.apache.ambari.server.state.action.ActionInitEvent;
import org.junit.Assert;
import org.junit.Test;

public class JobTest {

  private Action createNewJob(long id, String jobName, long startTime) {
    ActionId jId = new ActionId(id, new ActionType(jobName));
    Action job = new ActionImpl(jId, startTime);
    return job;
  }

  private Action getRunningJob(long id, String jobName, long startTime)
      throws Exception {
    Action job = createNewJob(id, jobName, startTime);
    verifyProgressUpdate(job, ++startTime);
    return job;
  }

  private Action getCompletedJob(long id, String jobName, long startTime,
      boolean failedJob) throws Exception {
    Action job = getRunningJob(1, "JobNameFoo", startTime);
    completeJob(job, failedJob, ++startTime);
    return job;
  }

  private void verifyNewJob(Action job, long startTime) {
    Assert.assertEquals(ActionState.INIT, job.getState());
    Assert.assertEquals(startTime, job.getStartTime());
  }


  private void verifyProgressUpdate(Action job, long updateTime)
      throws Exception {
    ActionProgressUpdateEvent e = new ActionProgressUpdateEvent(job.getId(),
        updateTime);
    job.handleEvent(e);
    Assert.assertEquals(ActionState.IN_PROGRESS, job.getState());
    Assert.assertEquals(updateTime, job.getLastUpdateTime());
  }


  private void completeJob(Action job, boolean failJob, long endTime)
      throws Exception {
    ActionEvent e = null;
    ActionState endState = null;
    if (failJob) {
      e = new ActionFailedEvent(job.getId(), endTime);
      endState = ActionState.FAILED;
    } else {
      e = new ActionCompletedEvent(job.getId(), endTime);
      endState = ActionState.COMPLETED;
    }
    job.handleEvent(e);
    Assert.assertEquals(endState, job.getState());
    Assert.assertEquals(endTime, job.getLastUpdateTime());
    Assert.assertEquals(endTime, job.getCompletionTime());
  }


  @Test public void reInitCompletedJob() throws Exception{Action job=getCompletedJob(1,"JobNameFoo",1,false);ActionId jId=new ActionId(2,new ActionType("JobNameFoo"));ActionInitEvent e=new ActionInitEvent(jId,100);job.handleEvent(e);Assert.assertEquals(ActionState.INIT,job.getState());Assert.assertEquals(100,job.getStartTime());Assert.assertEquals(-1,job.getLastUpdateTime());Assert.assertEquals(-1,job.getCompletionTime());Assert.assertEquals(2,job.getId().actionId);}


}
