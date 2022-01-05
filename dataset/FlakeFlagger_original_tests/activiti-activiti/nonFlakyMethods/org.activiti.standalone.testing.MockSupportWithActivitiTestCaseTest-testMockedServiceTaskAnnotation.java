@Deployment @MockServiceTask(originalClassName="com.yourcompany.delegate",mockedClassName="org.activiti.standalone.testing.helpers.ServiceTaskTestMock") public void testMockedServiceTaskAnnotation(){
  assertEquals(0,ServiceTaskTestMock.CALL_COUNT.get());
  runtimeService.startProcessInstanceByKey("mockSupportTest");
  assertEquals(1,ServiceTaskTestMock.CALL_COUNT.get());
}
