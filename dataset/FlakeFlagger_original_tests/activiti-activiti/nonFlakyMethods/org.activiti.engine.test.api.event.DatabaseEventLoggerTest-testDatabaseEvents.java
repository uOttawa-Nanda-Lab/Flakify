@Deployment(resources={"org/activiti/engine/test/api/event/DatabaseEventLoggerProcess.bpmn20.xml"}) public void testDatabaseEvents() throws IOException {
  String testTenant="testTenant";
  String deploymentId=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/event/DatabaseEventLoggerProcess.bpmn20.xml").tenantId(testTenant).deploy().getId();
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKeyAndTenantId("DatabaseEventLoggerProcess",CollectionUtil.singletonMap("testVar","helloWorld"),testTenant);
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
  long lastLogNr=-1;
  for (int i=0; i < eventLogEntries.size(); i++) {
    EventLogEntry entry=eventLogEntries.get(i);
    if (i == 0) {
      assertNotNull(entry.getType());
      assertEquals(ActivitiEventType.VARIABLE_CREATED.name(),entry.getType());
      assertNotNull(entry.getProcessDefinitionId());
      assertNotNull(entry.getProcessInstanceId());
      assertNotNull(entry.getTimeStamp());
      assertNull(entry.getTaskId());
      Map<String,Object> data=objectMapper.readValue(entry.getData(),new TypeReference<HashMap<String,Object>>(){
      }
);
      assertNotNull(data.get(Fields.PROCESS_DEFINITION_ID));
      assertNotNull(data.get(Fields.PROCESS_INSTANCE_ID));
      assertNotNull(data.get(Fields.VALUE_STRING));
      assertEquals(testTenant,data.get(Fields.TENANT_ID));
    }
    if (i == 1) {
      assertNotNull(entry.getType());
      assertEquals("PROCESSINSTANCE_START",entry.getType());
      assertNotNull(entry.getProcessDefinitionId());
      assertNotNull(entry.getProcessInstanceId());
      assertNotNull(entry.getTimeStamp());
      assertNull(entry.getExecutionId());
      assertNull(entry.getTaskId());
      Map<String,Object> data=objectMapper.readValue(entry.getData(),new TypeReference<HashMap<String,Object>>(){
      }
);
      assertNotNull(data.get(Fields.ID));
      assertNotNull(data.get(Fields.PROCESS_DEFINITION_ID));
      assertNotNull(data.get(Fields.TENANT_ID));
      assertEquals(testTenant,data.get(Fields.TENANT_ID));
      Map<String,Object> variableMap=(Map<String,Object>)data.get(Fields.VARIABLES);
      assertEquals(1,variableMap.size());
      assertEquals("helloWorld",variableMap.get("testVar"));
      assertFalse(data.containsKey(Fields.NAME));
      assertFalse(data.containsKey(Fields.BUSINESS_KEY));
    }
    if (i == 2 || i == 5 || i == 9 || i == 12) {
      assertNotNull(entry.getType());
      assertEquals(ActivitiEventType.ACTIVITY_STARTED.name(),entry.getType());
      assertNotNull(entry.getProcessDefinitionId());
      assertNotNull(entry.getProcessInstanceId());
      assertNotNull(entry.getTimeStamp());
      assertNotNull(entry.getExecutionId());
      assertNull(entry.getTaskId());
      Map<String,Object> data=objectMapper.readValue(entry.getData(),new TypeReference<HashMap<String,Object>>(){
      }
);
      assertNotNull(data.get(Fields.ACTIVITY_ID));
      assertNotNull(data.get(Fields.PROCESS_DEFINITION_ID));
      assertNotNull(data.get(Fields.PROCESS_INSTANCE_ID));
      assertNotNull(data.get(Fields.EXECUTION_ID));
      assertNotNull(data.get(Fields.ACTIVITY_TYPE));
      assertEquals(testTenant,data.get(Fields.TENANT_ID));
    }
    if (i == 3) {
      assertNotNull(entry.getType());
      assertEquals(ActivitiEventType.ACTIVITY_COMPLETED.name(),entry.getType());
      assertNotNull(entry.getProcessDefinitionId());
      assertNotNull(entry.getProcessInstanceId());
      assertNotNull(entry.getTimeStamp());
      assertNotNull(entry.getExecutionId());
      assertNull(entry.getTaskId());
      Map<String,Object> data=objectMapper.readValue(entry.getData(),new TypeReference<HashMap<String,Object>>(){
      }
);
      assertNotNull(data.get(Fields.ACTIVITY_ID));
      assertEquals("startEvent1",data.get(Fields.ACTIVITY_ID));
      assertNotNull(data.get(Fields.PROCESS_DEFINITION_ID));
      assertNotNull(data.get(Fields.PROCESS_INSTANCE_ID));
      assertNotNull(data.get(Fields.EXECUTION_ID));
      assertNotNull(data.get(Fields.ACTIVITY_TYPE));
      assertEquals(testTenant,data.get(Fields.TENANT_ID));
    }
    if (i == 4 || i == 7 || i == 8) {
      assertNotNull(entry.getType());
      assertEquals(ActivitiEventType.SEQUENCEFLOW_TAKEN.name(),entry.getType());
      assertNotNull(entry.getProcessDefinitionId());
      assertNotNull(entry.getProcessInstanceId());
      assertNotNull(entry.getTimeStamp());
      assertNotNull(entry.getExecutionId());
      assertNull(entry.getTaskId());
      Map<String,Object> data=objectMapper.readValue(entry.getData(),new TypeReference<HashMap<String,Object>>(){
      }
);
      assertNotNull(data.get(Fields.ID));
      assertNotNull(data.get(Fields.SOURCE_ACTIVITY_ID));
      assertNotNull(data.get(Fields.SOURCE_ACTIVITY_NAME));
      assertNotNull(data.get(Fields.SOURCE_ACTIVITY_TYPE));
      assertEquals(testTenant,data.get(Fields.TENANT_ID));
    }
    if (i == 6) {
      assertNotNull(entry.getType());
      assertEquals(ActivitiEventType.ACTIVITY_COMPLETED.name(),entry.getType());
      assertNotNull(entry.getProcessDefinitionId());
      assertNotNull(entry.getProcessInstanceId());
      assertNotNull(entry.getTimeStamp());
      assertNotNull(entry.getExecutionId());
      assertNull(entry.getTaskId());
      Map<String,Object> data=objectMapper.readValue(entry.getData(),new TypeReference<HashMap<String,Object>>(){
      }
);
      assertNotNull(data.get(Fields.ACTIVITY_ID));
      assertNotNull(data.get(Fields.PROCESS_DEFINITION_ID));
      assertNotNull(data.get(Fields.PROCESS_INSTANCE_ID));
      assertNotNull(data.get(Fields.EXECUTION_ID));
      assertNotNull(data.get(Fields.ACTIVITY_TYPE));
      assertEquals(testTenant,data.get(Fields.TENANT_ID));
    }
    if (i == 11 || i == 14) {
      assertNotNull(entry.getType());
      assertEquals(ActivitiEventType.TASK_ASSIGNED.name(),entry.getType());
      assertNotNull(entry.getTimeStamp());
      assertNotNull(entry.getProcessDefinitionId());
      assertNotNull(entry.getProcessInstanceId());
      assertNotNull(entry.getExecutionId());
      assertNotNull(entry.getTaskId());
      Map<String,Object> data=objectMapper.readValue(entry.getData(),new TypeReference<HashMap<String,Object>>(){
      }
);
      assertNotNull(data.get(Fields.ID));
      assertNotNull(data.get(Fields.NAME));
      assertNotNull(data.get(Fields.ASSIGNEE));
      assertNotNull(data.get(Fields.CREATE_TIME));
      assertNotNull(data.get(Fields.PRIORITY));
      assertNotNull(data.get(Fields.PROCESS_DEFINITION_ID));
      assertNotNull(data.get(Fields.EXECUTION_ID));
      assertNotNull(data.get(Fields.TENANT_ID));
      assertFalse(data.containsKey(Fields.DESCRIPTION));
      assertFalse(data.containsKey(Fields.CATEGORY));
      assertFalse(data.containsKey(Fields.OWNER));
      assertFalse(data.containsKey(Fields.DUE_DATE));
      assertFalse(data.containsKey(Fields.FORM_KEY));
      assertFalse(data.containsKey(Fields.USER_ID));
      assertEquals(testTenant,data.get(Fields.TENANT_ID));
    }
    if (i == 10 || i == 13) {
      assertNotNull(entry.getType());
      assertEquals(ActivitiEventType.TASK_CREATED.name(),entry.getType());
      assertNotNull(entry.getTimeStamp());
      assertNotNull(entry.getProcessDefinitionId());
      assertNotNull(entry.getProcessInstanceId());
      assertNotNull(entry.getExecutionId());
      assertNotNull(entry.getTaskId());
      Map<String,Object> data=objectMapper.readValue(entry.getData(),new TypeReference<HashMap<String,Object>>(){
      }
);
      assertNotNull(data.get(Fields.ID));
      assertNotNull(data.get(Fields.NAME));
      assertNotNull(data.get(Fields.ASSIGNEE));
      assertNotNull(data.get(Fields.CREATE_TIME));
      assertNotNull(data.get(Fields.PRIORITY));
      assertNotNull(data.get(Fields.PROCESS_DEFINITION_ID));
      assertNotNull(data.get(Fields.EXECUTION_ID));
      assertNotNull(data.get(Fields.TENANT_ID));
      assertFalse(data.containsKey(Fields.DESCRIPTION));
      assertFalse(data.containsKey(Fields.CATEGORY));
      assertFalse(data.containsKey(Fields.OWNER));
      assertFalse(data.containsKey(Fields.DUE_DATE));
      assertFalse(data.containsKey(Fields.FORM_KEY));
      assertFalse(data.containsKey(Fields.USER_ID));
      assertEquals(testTenant,data.get(Fields.TENANT_ID));
    }
    lastLogNr=entry.getLogNumber();
  }
  for (  Task task : taskService.createTaskQuery().list()) {
    Authentication.setAuthenticatedUserId(task.getAssignee());
    Map<String,Object> varMap=new HashMap<String,Object>();
    varMap.put("test","test");
    taskService.complete(task.getId(),varMap);
    Authentication.setAuthenticatedUserId(null);
  }
  eventLogEntries=managementService.getEventLogEntries(lastLogNr,100L);
  assertEquals(17,eventLogEntries.size());
  for (int i=0; i < eventLogEntries.size(); i++) {
    EventLogEntry entry=eventLogEntries.get(i);
    if (i == 1 || i == 6) {
      assertNotNull(entry.getType());
      assertEquals(ActivitiEventType.TASK_COMPLETED.name(),entry.getType());
      assertNotNull(entry.getProcessDefinitionId());
      assertNotNull(entry.getProcessInstanceId());
      assertNotNull(entry.getExecutionId());
      assertNotNull(entry.getTaskId());
      Map<String,Object> data=objectMapper.readValue(entry.getData(),new TypeReference<HashMap<String,Object>>(){
      }
);
      assertNotNull(data.get(Fields.ID));
      assertNotNull(data.get(Fields.NAME));
      assertNotNull(data.get(Fields.ASSIGNEE));
      assertNotNull(data.get(Fields.CREATE_TIME));
      assertNotNull(data.get(Fields.PRIORITY));
      assertNotNull(data.get(Fields.PROCESS_DEFINITION_ID));
      assertNotNull(data.get(Fields.EXECUTION_ID));
      assertNotNull(data.get(Fields.TENANT_ID));
      assertNotNull(data.get(Fields.USER_ID));
      Map<String,Object> variableMap=(Map<String,Object>)data.get(Fields.VARIABLES);
      assertEquals(1,variableMap.size());
      assertEquals("test",variableMap.get("test"));
      assertFalse(data.containsKey(Fields.DESCRIPTION));
      assertFalse(data.containsKey(Fields.CATEGORY));
      assertFalse(data.containsKey(Fields.OWNER));
      assertFalse(data.containsKey(Fields.DUE_DATE));
      assertFalse(data.containsKey(Fields.FORM_KEY));
      assertEquals(testTenant,data.get(Fields.TENANT_ID));
    }
    if (i == 2 || i == 7 || i == 10 || i == 13) {
      assertNotNull(entry.getType());
      assertEquals(ActivitiEventType.ACTIVITY_COMPLETED.name(),entry.getType());
      assertNotNull(entry.getProcessDefinitionId());
      assertNotNull(entry.getProcessInstanceId());
      assertNotNull(entry.getTimeStamp());
      assertNotNull(entry.getExecutionId());
      assertNull(entry.getTaskId());
      Map<String,Object> data=objectMapper.readValue(entry.getData(),new TypeReference<HashMap<String,Object>>(){
      }
);
      assertNotNull(data.get(Fields.ACTIVITY_ID));
      assertNotNull(data.get(Fields.PROCESS_DEFINITION_ID));
      assertNotNull(data.get(Fields.PROCESS_INSTANCE_ID));
      assertNotNull(data.get(Fields.EXECUTION_ID));
      assertNotNull(data.get(Fields.ACTIVITY_TYPE));
      assertNotNull(data.get(Fields.BEHAVIOR_CLASS));
      assertEquals(testTenant,data.get(Fields.TENANT_ID));
      if (i == 2) {
        assertEquals("userTask",data.get(Fields.ACTIVITY_TYPE));
      }
 else       if (i == 7) {
        assertEquals("userTask",data.get(Fields.ACTIVITY_TYPE));
      }
 else       if (i == 10) {
        assertEquals("parallelGateway",data.get(Fields.ACTIVITY_TYPE));
      }
 else       if (i == 13) {
        assertEquals("endEvent",data.get(Fields.ACTIVITY_TYPE));
      }
    }
    if (i == 3 || i == 8 || i == 11) {
      assertNotNull(entry.getType());
      assertEquals(entry.getType(),ActivitiEventType.SEQUENCEFLOW_TAKEN.name());
      assertNotNull(entry.getProcessDefinitionId());
      assertNotNull(entry.getProcessInstanceId());
      assertNotNull(entry.getTimeStamp());
      assertNotNull(entry.getExecutionId());
      assertNull(entry.getTaskId());
      Map<String,Object> data=objectMapper.readValue(entry.getData(),new TypeReference<HashMap<String,Object>>(){
      }
);
      assertNotNull(data.get(Fields.ID));
      assertNotNull(data.get(Fields.SOURCE_ACTIVITY_ID));
      assertNotNull(data.get(Fields.SOURCE_ACTIVITY_TYPE));
      assertNotNull(data.get(Fields.SOURCE_ACTIVITY_BEHAVIOR_CLASS));
      assertNotNull(data.get(Fields.TARGET_ACTIVITY_ID));
      assertNotNull(data.get(Fields.TARGET_ACTIVITY_TYPE));
      assertNotNull(data.get(Fields.TARGET_ACTIVITY_BEHAVIOR_CLASS));
      assertEquals(testTenant,data.get(Fields.TENANT_ID));
    }
    if (i == 14 || i == 15) {
      assertNotNull(entry.getType());
      assertEquals("VARIABLE_DELETED",entry.getType());
      assertNotNull(entry.getProcessDefinitionId());
      assertNotNull(entry.getProcessInstanceId());
      assertNotNull(entry.getTimeStamp());
      assertNotNull(entry.getExecutionId());
      assertNull(entry.getTaskId());
    }
    if (i == 16) {
      assertNotNull(entry.getType());
      assertEquals("PROCESSINSTANCE_END",entry.getType());
      assertNotNull(entry.getProcessDefinitionId());
      assertNotNull(entry.getProcessInstanceId());
      assertNotNull(entry.getTimeStamp());
      assertNull(entry.getExecutionId());
      assertNull(entry.getTaskId());
      Map<String,Object> data=objectMapper.readValue(entry.getData(),new TypeReference<HashMap<String,Object>>(){
      }
);
      assertNotNull(data.get(Fields.ID));
      assertNotNull(data.get(Fields.PROCESS_DEFINITION_ID));
      assertNotNull(data.get(Fields.TENANT_ID));
      assertFalse(data.containsKey(Fields.NAME));
      assertFalse(data.containsKey(Fields.BUSINESS_KEY));
      assertEquals(testTenant,data.get(Fields.TENANT_ID));
    }
  }
  for (  EventLogEntry eventLogEntry : managementService.getEventLogEntries(null,null)) {
    managementService.deleteEventLogEntry(eventLogEntry.getLogNumber());
  }
  repositoryService.deleteDeployment(deploymentId,true);
}
