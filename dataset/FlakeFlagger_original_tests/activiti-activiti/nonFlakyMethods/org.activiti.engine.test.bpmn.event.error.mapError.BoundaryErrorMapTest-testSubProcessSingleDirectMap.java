@Deployment public void testSubProcessSingleDirectMap(){
  FlagDelegate.reset();
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("exceptionClass",BoundaryErrorParentException.class.getName());
  runtimeService.startProcessInstanceByKey("subprocssWithSingleExceptionMap",vars);
  assertTrue(FlagDelegate.isVisited());
}
