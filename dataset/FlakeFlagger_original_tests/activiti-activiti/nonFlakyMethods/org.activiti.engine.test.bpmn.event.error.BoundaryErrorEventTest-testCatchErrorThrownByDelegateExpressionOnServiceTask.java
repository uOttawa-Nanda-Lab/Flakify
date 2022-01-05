@Deployment public void testCatchErrorThrownByDelegateExpressionOnServiceTask(){
  HashMap<String,Object> variables=new HashMap<String,Object>();
  variables.put("bpmnErrorBean",new BpmnErrorBean());
  String procId=runtimeService.startProcessInstanceByKey("testCatchErrorThrownByDelegateExpressionOnServiceTask",variables).getId();
  assertThatErrorHasBeenCaught(procId);
}
