@Deployment public void testCatchErrorThrownByScriptTaskInEmbeddedSubProcess(){
  String procId=runtimeService.startProcessInstanceByKey("CatchErrorThrownByScriptTaskInEmbeddedSubProcess").getId();
  assertThatErrorHasBeenCaught(procId);
}
