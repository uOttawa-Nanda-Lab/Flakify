@Deployment public void testCatchErrorThrownByJavaDelegateOnMultiInstanceServiceTaskParallel(){
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("executionsBeforeError",2);
  String procId=runtimeService.startProcessInstanceByKey("catchErrorThrownByJavaDelegateOnMultiInstanceServiceTaskParallel",variables).getId();
  assertThatErrorHasBeenCaught(procId);
}
