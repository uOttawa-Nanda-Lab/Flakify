@Deployment public void testClassDelegateDefaultMap(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("exceptionClass",Exception.class.getName());
  FlagDelegate.reset();
  runtimeService.startProcessInstanceByKey("processWithSingleExceptionMap",vars);
  assertTrue(FlagDelegate.isVisited());
}
