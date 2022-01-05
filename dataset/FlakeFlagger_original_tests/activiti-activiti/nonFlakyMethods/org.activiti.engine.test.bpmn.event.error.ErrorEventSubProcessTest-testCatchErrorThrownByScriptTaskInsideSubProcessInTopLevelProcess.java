@Deployment public void testCatchErrorThrownByScriptTaskInsideSubProcessInTopLevelProcess(){
  String procId=runtimeService.startProcessInstanceByKey("CatchErrorThrownByScriptTaskInsideSubProcessInTopLevelProcess").getId();
  assertThatErrorHasBeenCaught(procId);
}
