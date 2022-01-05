@Deployment public void testCustomActivityBehaviorFactory(){
  int nrOfProcessInstances=6;
  for (int i=0; i < nrOfProcessInstances; i++) {
    runtimeService.startProcessInstanceByKey("oneTaskProcess");
  }
  assertEquals(nrOfProcessInstances,COUNTER.get());
}
