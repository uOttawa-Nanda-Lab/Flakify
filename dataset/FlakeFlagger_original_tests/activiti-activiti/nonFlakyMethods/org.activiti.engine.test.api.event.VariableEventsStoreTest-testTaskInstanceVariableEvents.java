public void testTaskInstanceVariableEvents() throws Exception {
  Task task=taskService.newTask();
  taskService.saveTask(task);
  taskService.setVariableLocal(task.getId(),"myVar","value");
  assertEquals(1,listener.getEventsReceived().size());
  assertEquals(ActivitiEventType.VARIABLE_CREATED,listener.getEventsReceived().get(0).getType());
  assertEquals(1,managementService.getEventLogEntries(null,null).size());
  taskService.removeVariableLocal(task.getId(),"myVar");
  assertEquals(2,listener.getEventsReceived().size());
  assertEquals(ActivitiEventType.VARIABLE_DELETED,listener.getEventsReceived().get(1).getType());
  assertEquals(2,managementService.getEventLogEntries(null,null).size());
  Map<String,String> vars=new HashMap<String,String>();
  vars.put("myVar","value");
  vars.put("myVar2","value");
  taskService.setVariablesLocal(task.getId(),vars);
  taskService.removeVariablesLocal(task.getId(),Arrays.asList("myVar","myVar2"));
  assertEquals(6,listener.getEventsReceived().size());
  assertEquals(6,managementService.getEventLogEntries(null,null).size());
  taskService.complete(task.getId());
  historyService.deleteHistoricTaskInstance(task.getId());
}
