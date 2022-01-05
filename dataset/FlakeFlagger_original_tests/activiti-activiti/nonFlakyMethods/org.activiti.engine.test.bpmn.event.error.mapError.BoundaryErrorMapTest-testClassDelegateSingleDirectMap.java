@Deployment public void testClassDelegateSingleDirectMap(){
  FlagDelegate.reset();
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("exceptionClass",BoundaryErrorParentException.class.getName());
  runtimeService.startProcessInstanceByKey("processWithSingleExceptionMap",vars);
  assertTrue(FlagDelegate.isVisited());
}
