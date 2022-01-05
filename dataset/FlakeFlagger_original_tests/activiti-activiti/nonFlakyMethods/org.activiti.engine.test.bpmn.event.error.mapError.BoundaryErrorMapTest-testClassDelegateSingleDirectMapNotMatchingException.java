@Deployment(resources="org/activiti/engine/test/bpmn/event/error/mapError/BoundaryErrorMapTest.testClassDelegateSingleDirectMap.bpmn20.xml") public void testClassDelegateSingleDirectMapNotMatchingException(){
  FlagDelegate.reset();
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("exceptionClass",JAXBException.class.getName());
  assertEquals(0,ServiceTaskTestMock.CALL_COUNT.get());
  try {
    runtimeService.startProcessInstanceByKey("processWithSingleExceptionMap",vars);
    fail("exception expected, as there is no matching exception map");
  }
 catch (  Exception e) {
    assertFalse(FlagDelegate.isVisited());
  }
}
