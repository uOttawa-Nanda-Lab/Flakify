@Deployment public void testCustomListenerFactory(){
  int nrOfProcessInstances=4;
  for (int i=0; i < nrOfProcessInstances; i++) {
    runtimeService.startProcessInstanceByKey("oneTaskProcess");
  }
  assertEquals(nrOfProcessInstances * 100,COUNTER.get());
}
