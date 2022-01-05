@Deployment @MockServiceTasks({@MockServiceTask(originalClassName="com.yourcompany.delegate1",mockedClassName="org.activiti.standalone.testing.helpers.ServiceTaskTestMock"),@MockServiceTask(originalClassName="com.yourcompany.delegate2",mockedClassName="org.activiti.standalone.testing.helpers.ServiceTaskTestMock")}) public void testMockedServiceTasksAnnotation(){
  assertEquals(0,ServiceTaskTestMock.CALL_COUNT.get());
  runtimeService.startProcessInstanceByKey("mockSupportTest");
  assertEquals(2,ServiceTaskTestMock.CALL_COUNT.get());
}
