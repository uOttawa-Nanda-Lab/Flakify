@Test public void assertPost() throws InterruptedException, NoSuchFieldException {
  jobEventBus=new JobEventBus(new TestJobEventConfiguration(jobEventCaller));
  assertIsRegistered(true);
  jobEventBus.post(new JobExecutionEvent("fake_task_id","test_event_bus_job",JobExecutionEvent.ExecutionSource.NORMAL_TRIGGER,0));
  while (!TestJobEventListener.isExecutionEventCalled()) {
    Thread.sleep(100L);
  }
  verify(jobEventCaller).call();
}
