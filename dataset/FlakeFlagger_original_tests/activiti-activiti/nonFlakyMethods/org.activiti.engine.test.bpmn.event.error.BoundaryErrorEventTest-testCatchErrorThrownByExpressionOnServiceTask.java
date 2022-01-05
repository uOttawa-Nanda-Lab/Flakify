@Deployment public void testCatchErrorThrownByExpressionOnServiceTask(){
  HashMap<String,Object> variables=new HashMap<String,Object>();
  variables.put("bpmnErrorBean",new BpmnErrorBean());
  String procId=runtimeService.startProcessInstanceByKey("testCatchErrorThrownByExpressionOnServiceTask",variables).getId();
  assertThatErrorHasBeenCaught(procId);
}
