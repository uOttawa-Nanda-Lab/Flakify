package org.activiti.standalone.testing;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ActivitiRuleJunit4SubclassTest {
@Test @Deployment(resources={"org/activiti/engine/test/bpmn/async/AsyncTaskTest.testAsyncTask.bpmn20.xml"}) public void testWaitForJobs(){
  RuntimeService runtimeService=activitiRule.getRuntimeService();
  ManagementService managementService=activitiRule.getManagementService();
  runtimeService.startProcessInstanceByKey("asyncTask");
  assertEquals(1,managementService.createJobQuery().count());
  JobTestHelper.waitForJobExecutorToProcessAllJobs(activitiRule,5000L,500L);
  assertEquals(0,managementService.createJobQuery().count());
}

}