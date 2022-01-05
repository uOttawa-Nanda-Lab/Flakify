@Deployment @NoOpServiceTasks public void testNoOpServiceTasksAnnotation(){
  assertEquals(0,mockSupport().getNrOfNoOpServiceTaskExecutions());
  runtimeService.startProcessInstanceByKey("mockSupportTest");
  assertEquals(5,mockSupport().getNrOfNoOpServiceTaskExecutions());
  for (int i=1; i <= 5; i++) {
    assertEquals("com.yourcompany.delegate" + i,mockSupport().getExecutedNoOpServiceTaskDelegateClassNames().get(i - 1));
  }
}
