@Deployment public void testCatchErrorThrownByScriptTaskInEmbeddedSubProcessWithErrorCode(){
  String procId=runtimeService.startProcessInstanceByKey("CatchErrorThrownByScriptTaskInEmbeddedSubProcessWithErrorCode").getId();
  assertThatErrorHasBeenCaught(procId);
}
