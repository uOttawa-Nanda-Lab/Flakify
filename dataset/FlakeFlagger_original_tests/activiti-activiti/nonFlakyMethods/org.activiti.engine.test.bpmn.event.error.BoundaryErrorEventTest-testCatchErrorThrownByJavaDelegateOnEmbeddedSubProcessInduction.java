@Deployment public void testCatchErrorThrownByJavaDelegateOnEmbeddedSubProcessInduction(){
  String procId=runtimeService.startProcessInstanceByKey("catchErrorThrownByJavaDelegateOnEmbeddedSubProcessInduction").getId();
  assertThatErrorHasBeenCaught(procId);
}
