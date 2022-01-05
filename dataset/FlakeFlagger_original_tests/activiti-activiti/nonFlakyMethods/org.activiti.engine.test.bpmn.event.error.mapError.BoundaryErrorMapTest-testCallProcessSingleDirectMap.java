@Deployment(resources={"org/activiti/engine/test/bpmn/event/error/mapError/BoundaryErrorMapTest.testCallProcessSingleDirectMap.bpmn20.xml","org/activiti/engine/test/bpmn/event/error/mapError/BoundaryErrorMapTest.testCallProcessCalee.bpmn20.xml"}) public void testCallProcessSingleDirectMap(){
  FlagDelegate.reset();
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("exceptionClass",BoundaryErrorParentException.class.getName());
  runtimeService.startProcessInstanceByKey("callProcssWithSingleExceptionMap",vars);
  assertTrue(FlagDelegate.isVisited());
}
