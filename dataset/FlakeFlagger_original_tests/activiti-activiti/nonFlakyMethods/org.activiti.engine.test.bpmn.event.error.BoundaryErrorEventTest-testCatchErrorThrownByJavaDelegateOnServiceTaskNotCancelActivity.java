@Deployment public void testCatchErrorThrownByJavaDelegateOnServiceTaskNotCancelActivity(){
  String procId=runtimeService.startProcessInstanceByKey("catchErrorThrownByJavaDelegateOnServiceTaskNotCancelActiviti").getId();
  assertThatErrorHasBeenCaught(procId);
}
