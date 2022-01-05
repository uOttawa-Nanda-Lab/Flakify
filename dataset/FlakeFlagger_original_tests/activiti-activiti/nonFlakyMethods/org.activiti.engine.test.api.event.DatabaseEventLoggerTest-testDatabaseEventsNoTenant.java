public void testDatabaseEventsNoTenant() throws IOException {
  String deploymentId=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/event/DatabaseEventLoggerProcess.bpmn20.xml").deploy().getId();
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("DatabaseEventLoggerProcess",CollectionUtil.singletonMap("testVar","helloWorld"));
  List<EventLogEntry> eventLogEntries=managementService.getEventLogEntries(null,null);
  String processDefinitionId=processInstance.getProcessDefinitionId();
  Iterator<EventLogEntry> iterator=eventLogEntries.iterator();
  while (iterator.hasNext()) {
    EventLogEntry entry=iterator.next();
    if (entry.getProcessDefinitionId() != null && !entry.getProcessDefinitionId().equals(processDefinitionId)) {
      iterator.remove();
    }
  }
  assertEquals(15,eventLogEntries.size());
  for (int i=0; i < eventLogEntries.size(); i++) {
    EventLogEntry entry=eventLogEntries.get(i);
    if (i == 0) {
      assertEquals(entry.getType(),ActivitiEventType.VARIABLE_CREATED.name());
      Map<String,Object> data=objectMapper.readValue(entry.getData(),new TypeReference<HashMap<String,Object>>(){
      }
);
      assertNull(data.get(Fields.TENANT_ID));
    }
    if (i == 1) {
      assertEquals("PROCESSINSTANCE_START",entry.getType());
      Map<String,Object> data=objectMapper.readValue(entry.getData(),new TypeReference<HashMap<String,Object>>(){
      }
);
      assertNull(data.get(Fields.TENANT_ID));
    }
    if (i == 2 || i == 5 || i == 9 || i == 12) {
      assertEquals(entry.getType(),ActivitiEventType.ACTIVITY_STARTED.name());
      Map<String,Object> data=objectMapper.readValue(entry.getData(),new TypeReference<HashMap<String,Object>>(){
      }
);
      assertNull(data.get(Fields.TENANT_ID));
    }
    if (i == 3) {
      assertEquals(entry.getType(),ActivitiEventType.ACTIVITY_COMPLETED.name());
      Map<String,Object> data=objectMapper.readValue(entry.getData(),new TypeReference<HashMap<String,Object>>(){
      }
);
      assertNull(data.get(Fields.TENANT_ID));
    }
    if (i == 4 || i == 7 || i == 8) {
      assertEquals(entry.getType(),ActivitiEventType.SEQUENCEFLOW_TAKEN.name());
      Map<String,Object> data=objectMapper.readValue(entry.getData(),new TypeReference<HashMap<String,Object>>(){
      }
);
      assertNull(data.get(Fields.TENANT_ID));
    }
    if (i == 6) {
      assertEquals(entry.getType(),ActivitiEventType.ACTIVITY_COMPLETED.name());
      Map<String,Object> data=objectMapper.readValue(entry.getData(),new TypeReference<HashMap<String,Object>>(){
      }
);
      assertNull(data.get(Fields.TENANT_ID));
    }
    if (i == 11 || i == 14) {
      assertEquals(entry.getType(),ActivitiEventType.TASK_ASSIGNED.name());
      Map<String,Object> data=objectMapper.readValue(entry.getData(),new TypeReference<HashMap<String,Object>>(){
      }
);
      assertNull(data.get(Fields.TENANT_ID));
    }
    if (i == 10 || i == 13) {
      assertEquals(entry.getType(),ActivitiEventType.TASK_CREATED.name());
      Map<String,Object> data=objectMapper.readValue(entry.getData(),new TypeReference<HashMap<String,Object>>(){
      }
);
      assertNull(data.get(Fields.TENANT_ID));
    }
  }
  repositoryService.deleteDeployment(deploymentId,true);
  for (  EventLogEntry eventLogEntry : managementService.getEventLogEntries(null,null)) {
    managementService.deleteEventLogEntry(eventLogEntry.getLogNumber());
  }
}
