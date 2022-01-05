public void testStandaloneTaskEvents() throws JsonParseException, JsonMappingException, IOException {
  Task task=taskService.newTask();
  task.setAssignee("kermit");
  task.setTenantId("myTenant");
  taskService.saveTask(task);
  taskService.setAssignee(task.getId(),"gonzo");
  task=taskService.createTaskQuery().taskId(task.getId()).singleResult();
  task.setAssignee("kermit");
  taskService.saveTask(task);
  List<EventLogEntry> events=managementService.getEventLogEntries(null,null);
  assertEquals(4,events.size());
  assertEquals("TASK_CREATED",events.get(0).getType());
  assertEquals("TASK_ASSIGNED",events.get(1).getType());
  assertEquals("TASK_ASSIGNED",events.get(2).getType());
  assertEquals("TASK_ASSIGNED",events.get(3).getType());
  for (  EventLogEntry eventLogEntry : events) {
    Map<String,Object> data=objectMapper.readValue(eventLogEntry.getData(),new TypeReference<HashMap<String,Object>>(){
    }
);
    assertEquals("myTenant",data.get(Fields.TENANT_ID));
  }
  taskService.deleteTask(task.getId(),true);
  for (  EventLogEntry eventLogEntry : managementService.getEventLogEntries(null,null)) {
    managementService.deleteEventLogEntry(eventLogEntry.getLogNumber());
  }
}
