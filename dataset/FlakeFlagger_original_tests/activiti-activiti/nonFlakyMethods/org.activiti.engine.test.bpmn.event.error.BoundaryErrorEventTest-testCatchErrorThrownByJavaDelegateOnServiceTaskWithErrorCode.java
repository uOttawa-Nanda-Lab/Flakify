@Deployment public void testCatchErrorThrownByJavaDelegateOnServiceTaskWithErrorCode(){
  String procId=runtimeService.startProcessInstanceByKey("catchErrorThrownByJavaDelegateOnServiceTaskWithErrorCode").getId();
  assertThatErrorHasBeenCaught(procId);
}
