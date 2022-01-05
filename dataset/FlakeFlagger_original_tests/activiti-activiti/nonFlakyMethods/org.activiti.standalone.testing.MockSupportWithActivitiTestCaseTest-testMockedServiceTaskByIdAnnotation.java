@Deployment(resources={"org/activiti/standalone/testing/MockSupportWithActivitiTestCaseTest.testMockedServiceTaskAnnotation.bpmn20.xml"}) @MockServiceTask(id="serviceTask",mockedClassName="org.activiti.standalone.testing.helpers.ServiceTaskTestMock") public void testMockedServiceTaskByIdAnnotation(){
  assertEquals(0,ServiceTaskTestMock.CALL_COUNT.get());
  runtimeService.startProcessInstanceByKey("mockSupportTest");
  assertEquals(1,ServiceTaskTestMock.CALL_COUNT.get());
}
