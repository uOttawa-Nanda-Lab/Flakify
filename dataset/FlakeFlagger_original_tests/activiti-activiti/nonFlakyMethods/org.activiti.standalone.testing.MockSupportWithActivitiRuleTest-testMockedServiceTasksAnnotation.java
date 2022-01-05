@Test @Deployment @MockServiceTasks({@MockServiceTask(originalClassName="com.yourcompany.delegate1",mockedClassName="org.activiti.standalone.testing.helpers.ServiceTaskTestMock"),@MockServiceTask(originalClassName="com.yourcompany.delegate2",mockedClassName="org.activiti.standalone.testing.helpers.ServiceTaskTestMock")}) public void testMockedServiceTasksAnnotation(){
  Assert.assertEquals(0,ServiceTaskTestMock.CALL_COUNT.get());
  activitiRule.getRuntimeService().startProcessInstanceByKey("mockSupportTest");
  Assert.assertEquals(2,ServiceTaskTestMock.CALL_COUNT.get());
}
