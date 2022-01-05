@Deployment public void testCatchErrorThrownByJavaDelegateOnEmbeddedSubProcess(){
  String procId=runtimeService.startProcessInstanceByKey("catchErrorThrownByJavaDelegateOnEmbeddedSubProcess").getId();
  assertThatErrorHasBeenCaught(procId);
}
