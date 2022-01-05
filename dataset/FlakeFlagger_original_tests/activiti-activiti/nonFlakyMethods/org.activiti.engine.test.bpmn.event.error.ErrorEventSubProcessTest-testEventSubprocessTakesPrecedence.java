@Deployment public void testEventSubprocessTakesPrecedence(){
  String procId=runtimeService.startProcessInstanceByKey("CatchErrorInEmbeddedSubProcess").getId();
  assertThatErrorHasBeenCaught(procId);
}
