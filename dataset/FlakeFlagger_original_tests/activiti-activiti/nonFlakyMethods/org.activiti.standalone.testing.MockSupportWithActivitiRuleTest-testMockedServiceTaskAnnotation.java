@Test @Deployment @MockServiceTask(originalClassName="com.yourcompany.delegate",mockedClassName="org.activiti.standalone.testing.helpers.ServiceTaskTestMock") public void testMockedServiceTaskAnnotation(){
  Assert.assertEquals(0,ServiceTaskTestMock.CALL_COUNT.get());
  activitiRule.getRuntimeService().startProcessInstanceByKey("mockSupportTest");
  Assert.assertEquals(1,ServiceTaskTestMock.CALL_COUNT.get());
}
