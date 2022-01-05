@Deployment public void testParallel(){
  runtimeService.startProcessInstanceByKey("myProc");
}
