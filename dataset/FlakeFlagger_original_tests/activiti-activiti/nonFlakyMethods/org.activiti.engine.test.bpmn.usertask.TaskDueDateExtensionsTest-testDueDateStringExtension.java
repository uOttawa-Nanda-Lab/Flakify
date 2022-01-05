@Deployment public void testDueDateStringExtension() throws Exception {
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("dateVariable","1986-07-06T12:10:00");
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("dueDateExtension",variables);
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task.getDueDate());
  Date date=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse("06-07-1986 12:10:00");
  assertEquals(date,task.getDueDate());
}
