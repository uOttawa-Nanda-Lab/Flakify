@Test public void assertPostWithoutListener() throws NoSuchFieldException {
  jobEventBus=new JobEventBus();
  assertIsRegistered(false);
  ReflectionUtils.setFieldValue(jobEventBus,"eventBus",eventBus);
  jobEventBus.post(new JobExecutionEvent("fake_task_id","test_event_bus_job",JobExecutionEvent.ExecutionSource.NORMAL_TRIGGER,0));
  verify(eventBus,times(0)).post(ArgumentMatchers.<JobEvent>any());
}
