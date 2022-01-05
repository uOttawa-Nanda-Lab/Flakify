@Deployment public void testCatchErrorThrownByScriptTaskInTopLevelProcess(){
  String procId=runtimeService.startProcessInstanceByKey("CatchErrorThrownByScriptTaskInTopLevelProcess").getId();
  assertThatErrorHasBeenCaught(procId);
}
