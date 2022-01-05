@Deployment public void testExpression(){
  HashMap<String,Object> variables1=new HashMap<String,Object>();
  variables1.put("dueDate",new Date());
  HashMap<String,Object> variables2=new HashMap<String,Object>();
  variables2.put("dueDate",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));
  ProcessInstance pi1=runtimeService.startProcessInstanceByKey("intermediateTimerEventExample",variables1);
  ProcessInstance pi2=runtimeService.startProcessInstanceByKey("intermediateTimerEventExample",variables2);
  assertEquals(1,managementService.createTimerJobQuery().processInstanceId(pi1.getId()).count());
  assertEquals(1,managementService.createTimerJobQuery().processInstanceId(pi2.getId()).count());
  List<Job> jobs=managementService.createTimerJobQuery().executable().list();
  assertEquals(2,jobs.size());
  for (  Job job : jobs) {
    managementService.moveTimerToExecutableJob(job.getId());
    managementService.executeJob(job.getId());
  }
  assertEquals(0,managementService.createTimerJobQuery().processInstanceId(pi1.getId()).count());
  assertEquals(0,managementService.createTimerJobQuery().processInstanceId(pi2.getId()).count());
  assertProcessEnded(pi1.getProcessInstanceId());
  assertProcessEnded(pi2.getProcessInstanceId());
}
