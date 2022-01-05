@Deployment public void testRollBackOnException(){
  Exception expectedException=null;
  try {
    runtimeService.startProcessInstanceByKey("startProcessFromDelegate");
  }
 catch (  Exception e) {
    expectedException=e;
  }
  assertNotNull(expectedException);
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
}
