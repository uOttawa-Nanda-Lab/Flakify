public void testEventListenerConfiguration(){
  TestActivitiEventListener listener=(TestActivitiEventListener)processEngineConfiguration.getBeans().get("eventListener");
  assertNotNull(listener);
  listener.clearEventsReceived();
  ActivitiEvent event=new ActivitiEventImpl(ActivitiEventType.CUSTOM);
  processEngineConfiguration.getEventDispatcher().dispatchEvent(event);
  assertEquals(1,listener.getEventsReceived().size());
  assertEquals(event,listener.getEventsReceived().get(0));
  listener.clearEventsReceived();
  event=new ActivitiEventImpl(ActivitiEventType.ENTITY_DELETED);
  processEngineConfiguration.getEventDispatcher().dispatchEvent(event);
  event=new ActivitiEventImpl(ActivitiEventType.ENTITY_UPDATED);
  processEngineConfiguration.getEventDispatcher().dispatchEvent(event);
  assertEquals(2,listener.getEventsReceived().size());
  assertEquals(ActivitiEventType.ENTITY_DELETED,listener.getEventsReceived().get(0).getType());
  assertEquals(ActivitiEventType.ENTITY_UPDATED,listener.getEventsReceived().get(1).getType());
  listener.clearEventsReceived();
  event=new ActivitiEventImpl(ActivitiEventType.ENTITY_CREATED);
  processEngineConfiguration.getEventDispatcher().dispatchEvent(event);
  assertTrue(listener.getEventsReceived().isEmpty());
}
