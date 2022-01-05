@Deployment public void testDueDateExtension() throws Exception {
  Date date=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").parse("06-07-1986 12:10:00");
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("dateVariable",date);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("dueDateExtension",variables);
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task.getDueDate());
  assertEquals(date,task.getDueDate());
}
