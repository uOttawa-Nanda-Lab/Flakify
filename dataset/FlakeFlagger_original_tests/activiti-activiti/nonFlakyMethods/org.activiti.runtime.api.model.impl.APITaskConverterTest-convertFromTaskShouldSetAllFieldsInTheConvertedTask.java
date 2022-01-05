@Test public void convertFromTaskShouldSetAllFieldsInTheConvertedTask(){
  Date now=new Date();
  Task convertedTask=taskConverter.from(taskBuilder().withId("testTaskId").withAssignee("testUser").withName("testTaskName").withDescription("testTaskDescription").withCreatedDate(now).withClaimedDate(now).withDueDate(now).withPriority(112).withProcessDefinitionId("testProcessDefinitionId").withProcessInstanceId("testProcessInstanceId").withParentTaskId("testParentTaskId").build());
  assertThat(convertedTask).isNotNull().extracting(Task::getId,Task::getAssignee,Task::getName,Task::getDescription,Task::getCreatedDate,Task::getClaimedDate,Task::getDueDate,Task::getPriority,Task::getProcessDefinitionId,Task::getProcessInstanceId,Task::getParentTaskId,Task::getStatus).containsExactly("testTaskId","testUser","testTaskName","testTaskDescription",now,now,now,112,"testProcessDefinitionId","testProcessInstanceId","testParentTaskId",ASSIGNED);
}
