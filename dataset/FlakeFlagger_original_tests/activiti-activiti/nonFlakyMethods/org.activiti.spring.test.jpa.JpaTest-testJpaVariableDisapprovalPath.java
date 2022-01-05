public void testJpaVariableDisapprovalPath(){
  before();
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("customerName","Jane Doe");
  variables.put("amount",50000);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("LoanRequestProcess",variables);
  Object value=runtimeService.getVariable(processInstance.getId(),"loanRequest");
  assertNotNull(value);
  assertTrue(value instanceof LoanRequest);
  LoanRequest request=(LoanRequest)value;
  assertEquals("Jane Doe",request.getCustomerName());
  assertEquals(50000L,request.getAmount().longValue());
  assertFalse(request.isApproved());
  variables=new HashMap<String,Object>();
  variables.put("approvedByManager",Boolean.FALSE);
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  taskService.complete(task.getId(),variables);
  runtimeService.getVariable(processInstance.getId(),"loanRequest");
  request=(LoanRequest)value;
  assertFalse(request.isApproved());
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  assertEquals("Send rejection letter",task.getName());
  deleteDeployments();
}
