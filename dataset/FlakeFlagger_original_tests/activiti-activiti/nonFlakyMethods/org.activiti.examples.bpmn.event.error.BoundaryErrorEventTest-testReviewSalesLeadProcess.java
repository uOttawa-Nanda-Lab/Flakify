@Deployment(resources={"org/activiti/examples/bpmn/event/error/reviewSalesLead.bpmn20.xml"}) public void testReviewSalesLeadProcess(){
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("details","very interesting");
  variables.put("customerName","Alfresco");
  String procId=runtimeService.startProcessInstanceByKey("reviewSaledLead",variables).getId();
  Task task=taskService.createTaskQuery().taskAssignee("kermit").singleResult();
  assertEquals("Provide new sales lead",task.getName());
  taskService.complete(task.getId());
  Task ratingTask=taskService.createTaskQuery().taskCandidateGroup("accountancy").singleResult();
  assertEquals("Review customer rating",ratingTask.getName());
  Task profitabilityTask=taskService.createTaskQuery().taskCandidateGroup("management").singleResult();
  assertEquals("Review profitability",profitabilityTask.getName());
  variables=new HashMap<String,Object>();
  variables.put("notEnoughInformation",true);
  taskService.complete(profitabilityTask.getId(),variables);
  Task provideDetailsTask=taskService.createTaskQuery().taskAssignee("kermit").singleResult();
  assertEquals("Provide additional details",provideDetailsTask.getName());
  taskService.complete(provideDetailsTask.getId());
  List<Task> reviewTasks=taskService.createTaskQuery().orderByTaskName().asc().list();
  assertEquals("Review customer rating",reviewTasks.get(0).getName());
  assertEquals("Review profitability",reviewTasks.get(1).getName());
  taskService.complete(reviewTasks.get(0).getId());
  variables.put("notEnoughInformation",false);
  taskService.complete(reviewTasks.get(1).getId(),variables);
  assertProcessEnded(procId);
}
