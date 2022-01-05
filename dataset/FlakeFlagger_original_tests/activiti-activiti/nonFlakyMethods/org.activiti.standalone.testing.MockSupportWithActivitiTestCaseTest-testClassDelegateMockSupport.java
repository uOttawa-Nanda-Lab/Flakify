@Deployment public void testClassDelegateMockSupport(){
  assertEquals(0,ServiceTaskTestMock.CALL_COUNT.get());
  runtimeService.startProcessInstanceByKey("mockSupportTest");
  assertEquals(1,ServiceTaskTestMock.CALL_COUNT.get());
}
