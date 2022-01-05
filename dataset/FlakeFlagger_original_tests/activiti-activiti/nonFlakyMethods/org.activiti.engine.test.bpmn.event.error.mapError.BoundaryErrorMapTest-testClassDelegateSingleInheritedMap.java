@Deployment public void testClassDelegateSingleInheritedMap(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("exceptionClass",BoundaryEventChildException.class.getName());
  FlagDelegate.reset();
  runtimeService.startProcessInstanceByKey("processWithSingleExceptionMap",vars);
  assertTrue(FlagDelegate.isVisited());
}
