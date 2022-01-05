@Deployment public void testErrorThrownByJavaDelegateNotCaughtByOtherEventType(){
  String procId=runtimeService.startProcessInstanceByKey("testErrorThrownByJavaDelegateNotCaughtByOtherEventType").getId();
  assertThatErrorHasBeenCaught(procId);
}
