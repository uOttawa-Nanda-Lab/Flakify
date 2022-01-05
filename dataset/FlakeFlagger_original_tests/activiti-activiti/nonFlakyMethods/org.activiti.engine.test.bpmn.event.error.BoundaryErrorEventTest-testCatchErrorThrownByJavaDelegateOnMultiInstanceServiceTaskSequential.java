@Deployment public void testCatchErrorThrownByJavaDelegateOnMultiInstanceServiceTaskSequential(){
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("executionsBeforeError",2);
  String procId=runtimeService.startProcessInstanceByKey("catchErrorThrownByJavaDelegateOnMultiInstanceServiceTaskSequential",variables).getId();
  assertThatErrorHasBeenCaught(procId);
}
