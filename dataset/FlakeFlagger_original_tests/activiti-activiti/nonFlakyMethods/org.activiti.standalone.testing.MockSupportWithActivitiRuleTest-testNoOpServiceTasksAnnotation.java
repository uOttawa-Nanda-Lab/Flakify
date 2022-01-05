@Test @Deployment @NoOpServiceTasks public void testNoOpServiceTasksAnnotation(){
  Assert.assertEquals(0,activitiRule.mockSupport().getNrOfNoOpServiceTaskExecutions());
  activitiRule.getRuntimeService().startProcessInstanceByKey("mockSupportTest");
  Assert.assertEquals(5,activitiRule.mockSupport().getNrOfNoOpServiceTaskExecutions());
  for (int i=1; i <= 5; i++) {
    Assert.assertEquals("com.yourcompany.delegate" + i,activitiRule.mockSupport().getExecutedNoOpServiceTaskDelegateClassNames().get(i - 1));
  }
}
