@Deployment public void testRelativeDueDateStringExtension() throws Exception {
  Clock clock=processEngineConfiguration.getClock();
  clock.setCurrentCalendar(new GregorianCalendar(2015,0,1));
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("dateVariable","P2DT5H40M");
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("dueDateExtension",variables);
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task.getDueDate());
  Period period=new Period(task.getCreateTime().getTime(),task.getDueDate().getTime());
  assertEquals(2,period.getDays());
  assertEquals(5,period.getHours());
  assertEquals(40,period.getMinutes());
  clock.reset();
}
