@Deployment public void testRelativeDueDateStringWithCalendarNameExtension() throws Exception {
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("dateVariable","P2DT5H40M");
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("dueDateExtension",variables);
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task.getDueDate());
  assertEquals(task.getDueDate(),new Date(0));
}
