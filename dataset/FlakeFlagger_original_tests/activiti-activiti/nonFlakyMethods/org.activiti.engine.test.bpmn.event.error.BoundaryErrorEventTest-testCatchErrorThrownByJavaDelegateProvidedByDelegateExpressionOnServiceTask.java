@Deployment public void testCatchErrorThrownByJavaDelegateProvidedByDelegateExpressionOnServiceTask(){
  HashMap<String,Object> variables=new HashMap<String,Object>();
  variables.put("bpmnErrorBean",new BpmnErrorBean());
  String procId=runtimeService.startProcessInstanceByKey("testCatchErrorThrownByJavaDelegateProvidedByDelegateExpressionOnServiceTask",variables).getId();
  assertThatErrorHasBeenCaught(procId);
}
