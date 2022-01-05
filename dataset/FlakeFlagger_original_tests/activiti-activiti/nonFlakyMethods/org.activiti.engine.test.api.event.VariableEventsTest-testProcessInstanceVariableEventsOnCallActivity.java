@Deployment public void testProcessInstanceVariableEventsOnCallActivity() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("callVariableProcess",Collections.<String,Object>singletonMap("parentVar1","parentVar1Value"));
  assertNotNull(processInstance);
  assertEquals(6,listener.getEventsReceived().size());
  List<String> variableList=new ArrayList<String>();
  variableList.add("parentVar1");
  variableList.add("subVar1");
  variableList.add("parentVar2");
  ActivitiVariableEvent event=(ActivitiVariableEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.VARIABLE_CREATED,event.getType());
  assertTrue(variableList.contains(event.getVariableName()));
  event=(ActivitiVariableEvent)listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.VARIABLE_CREATED,event.getType());
  assertTrue(variableList.contains(event.getVariableName()));
  event=(ActivitiVariableEvent)listener.getEventsReceived().get(2);
  assertEquals(ActivitiEventType.VARIABLE_CREATED,event.getType());
  assertTrue(variableList.contains(event.getVariableName()));
  event=(ActivitiVariableEvent)listener.getEventsReceived().get(3);
  assertEquals(ActivitiEventType.VARIABLE_DELETED,event.getType());
  variableList.remove(event.getVariableName());
  event=(ActivitiVariableEvent)listener.getEventsReceived().get(4);
  assertEquals(ActivitiEventType.VARIABLE_DELETED,event.getType());
  variableList.remove(event.getVariableName());
  event=(ActivitiVariableEvent)listener.getEventsReceived().get(5);
  assertEquals(ActivitiEventType.VARIABLE_DELETED,event.getType());
  variableList.remove(event.getVariableName());
  assertEquals(0,variableList.size());
}
