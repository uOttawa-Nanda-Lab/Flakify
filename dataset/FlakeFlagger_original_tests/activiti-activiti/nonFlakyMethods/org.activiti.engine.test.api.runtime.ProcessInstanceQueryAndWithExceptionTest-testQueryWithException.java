public void testQueryWithException() throws InterruptedException {
  ProcessInstance processNoException=runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY_NO_EXCEPTION);
  ProcessInstanceQuery queryNoException=runtimeService.createProcessInstanceQuery();
  assertEquals(1,queryNoException.count());
  assertEquals(1,queryNoException.list().size());
  assertEquals(processNoException.getId(),queryNoException.list().get(0).getId());
  ProcessInstanceQuery queryWithException=runtimeService.createProcessInstanceQuery();
  assertEquals(0,queryWithException.withJobException().count());
  assertEquals(0,queryWithException.withJobException().list().size());
  ProcessInstance processWithException1=startProcessInstanceWithFailingJob(PROCESS_DEFINITION_KEY_WITH_EXCEPTION_1);
  TimerJobQuery jobQuery1=managementService.createTimerJobQuery().processInstanceId(processWithException1.getId());
  assertEquals(1,jobQuery1.withException().count());
  assertEquals(1,jobQuery1.withException().list().size());
  assertEquals(1,queryWithException.withJobException().count());
  assertEquals(1,queryWithException.withJobException().list().size());
  assertEquals(processWithException1.getId(),queryWithException.withJobException().list().get(0).getId());
  ProcessInstance processWithException2=startProcessInstanceWithFailingJob(PROCESS_DEFINITION_KEY_WITH_EXCEPTION_2);
  TimerJobQuery jobQuery2=managementService.createTimerJobQuery().processInstanceId(processWithException2.getId());
  assertEquals(2,jobQuery2.withException().count());
  assertEquals(2,jobQuery2.withException().list().size());
  assertEquals(2,queryWithException.withJobException().count());
  assertEquals(2,queryWithException.withJobException().list().size());
  assertEquals(processWithException1.getId(),queryWithException.withJobException().processDefinitionKey(PROCESS_DEFINITION_KEY_WITH_EXCEPTION_1).list().get(0).getId());
  assertEquals(processWithException2.getId(),queryWithException.withJobException().processDefinitionKey(PROCESS_DEFINITION_KEY_WITH_EXCEPTION_2).list().get(0).getId());
}
