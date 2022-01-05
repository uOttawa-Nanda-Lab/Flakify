@Deployment public void testMultipleSignalStartEvents(){
  runtimeService.signalEventReceived("signal1");
  validateTaskCounts(1,0,0);
  runtimeService.signalEventReceived("signal2");
  validateTaskCounts(1,1,0);
  runtimeService.signalEventReceived("signal3");
  validateTaskCounts(1,1,1);
  runtimeService.signalEventReceived("signal1");
  validateTaskCounts(2,1,1);
  runtimeService.signalEventReceived("signal1");
  validateTaskCounts(3,1,1);
  runtimeService.signalEventReceived("signal3");
  validateTaskCounts(3,1,2);
}
