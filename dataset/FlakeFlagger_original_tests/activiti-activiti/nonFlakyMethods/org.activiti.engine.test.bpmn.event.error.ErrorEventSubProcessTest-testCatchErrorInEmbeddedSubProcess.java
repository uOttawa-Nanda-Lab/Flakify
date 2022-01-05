@Deployment public void testCatchErrorInEmbeddedSubProcess(){
  String procId=runtimeService.startProcessInstanceByKey("CatchErrorInEmbeddedSubProcess").getId();
  assertThatErrorHasBeenCaught(procId);
}
