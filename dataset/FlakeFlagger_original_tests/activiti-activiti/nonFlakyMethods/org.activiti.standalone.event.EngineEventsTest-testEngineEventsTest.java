public void testEngineEventsTest(){
  TestActivitiEventListener listener=(TestActivitiEventListener)processEngineConfiguration.getBeans().get("eventListener");
  assertNotNull(listener);
  assertEquals(1,listener.getEventsReceived().size());
  assertEquals(ActivitiEventType.ENGINE_CREATED,listener.getEventsReceived().get(0).getType());
  listener.clearEventsReceived();
  processEngine.close();
  assertEquals(1,listener.getEventsReceived().size());
  assertEquals(ActivitiEventType.ENGINE_CLOSED,listener.getEventsReceived().get(0).getType());
}
