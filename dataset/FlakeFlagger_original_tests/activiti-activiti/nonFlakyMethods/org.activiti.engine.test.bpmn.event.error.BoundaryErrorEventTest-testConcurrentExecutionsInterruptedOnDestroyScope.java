@Deployment public void testConcurrentExecutionsInterruptedOnDestroyScope(){
  runtimeService.startProcessInstanceByKey("process");
}
