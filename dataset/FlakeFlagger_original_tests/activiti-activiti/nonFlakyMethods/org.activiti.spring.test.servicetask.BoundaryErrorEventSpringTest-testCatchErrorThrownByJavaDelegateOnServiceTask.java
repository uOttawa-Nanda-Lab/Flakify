@Deployment public void testCatchErrorThrownByJavaDelegateOnServiceTask(){
  String procId=runtimeService.startProcessInstanceByKey("catchErrorThrownByExpressionDelegateOnServiceTask").getId();
  assertThatErrorHasBeenCaught(procId);
}
