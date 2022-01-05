public void testEventListenerConfiguration(){
  TestActivitiEventListener listener=(TestActivitiEventListener)processEngineConfiguration.getBeans().get("eventListener");
  assertNotNull(listener);
  listener.clearEventsReceived();
  ActivitiEvent event=new ActivitiEventImpl(ActivitiEventType.CUSTOM);
  processEngineConfiguration.getEventDispatcher().dispatchEvent(event);
  assertEquals(1,listener.getEventsReceived().size());
  assertEquals(event,listener.getEventsReceived().get(0));
}
