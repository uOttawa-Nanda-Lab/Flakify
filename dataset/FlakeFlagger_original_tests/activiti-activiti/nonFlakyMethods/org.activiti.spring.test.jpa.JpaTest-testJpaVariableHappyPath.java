public void testJpaVariableHappyPath(){
  before();
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("customerName","John Doe");
  variables.put("amount",15000L);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("LoanRequestProcess",variables);
  Object value=runtimeService.getVariable(processInstance.getId(),"loanRequest");
  assertNotNull(value);
  assertTrue(value instanceof LoanRequest);
  LoanRequest request=(LoanRequest)value;
  assertEquals("John Doe",request.getCustomerName());
  assertEquals(15000L,request.getAmount().longValue());
  assertFalse(request.isApproved());
  variables=new HashMap<String,Object>();
  variables.put("approvedByManager",Boolean.TRUE);
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  taskService.complete(task.getId(),variables);
  assertEquals(0,runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).count());
  deleteDeployments();
}
