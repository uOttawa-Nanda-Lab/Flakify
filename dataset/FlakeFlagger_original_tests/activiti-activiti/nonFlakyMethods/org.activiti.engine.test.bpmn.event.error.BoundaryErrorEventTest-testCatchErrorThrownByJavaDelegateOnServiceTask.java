@Deployment public void testCatchErrorThrownByJavaDelegateOnServiceTask(){
  String procId=runtimeService.startProcessInstanceByKey("catchErrorThrownByJavaDelegateOnServiceTask").getId();
  assertThatErrorHasBeenCaught(procId);
}
