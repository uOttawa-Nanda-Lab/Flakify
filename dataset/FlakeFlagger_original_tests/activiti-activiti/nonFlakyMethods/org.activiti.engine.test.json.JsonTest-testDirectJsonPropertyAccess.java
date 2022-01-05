@Deployment public void testDirectJsonPropertyAccess(){
  Map<String,Object> vars=new HashMap<String,Object>();
  ObjectNode varNode=objectMapper.createObjectNode();
  varNode.put("var","myValue");
  vars.put("myJsonObj",varNode);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testJsonAvailableProcess",vars);
  ObjectNode value=(ObjectNode)runtimeService.getVariable(processInstance.getId(),"myJsonObj");
  assertNotNull(value);
  assertEquals("myValue",value.get("var").asText());
  Task task=taskService.createTaskQuery().active().singleResult();
  assertNotNull(task);
  ObjectNode var3Node=objectMapper.createObjectNode();
  var3Node.put("var","myValue");
  var3Node.put("var2","myOtherValue");
  var3Node.put("var3","myThirdValue");
  vars.put("myJsonObj",var3Node);
  taskService.complete(task.getId(),vars);
  value=(ObjectNode)runtimeService.getVariable(processInstance.getId(),"myJsonObj");
  assertNotNull(value);
  assertEquals("myValue",value.get("var").asText());
  assertEquals("myOtherValue",value.get("var2").asText());
  assertEquals("myThirdValue",value.get("var3").asText());
  task=taskService.createTaskQuery().active().singleResult();
  assertNotNull(task);
  assertEquals("userTaskSuccess",task.getTaskDefinitionKey());
}
