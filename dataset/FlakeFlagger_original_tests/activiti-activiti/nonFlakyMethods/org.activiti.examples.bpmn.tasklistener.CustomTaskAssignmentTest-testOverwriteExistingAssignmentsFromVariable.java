@Deployment public void testOverwriteExistingAssignmentsFromVariable(){
  Map<String,String> assigneeMappingTable=new HashMap<String,String>();
  assigneeMappingTable.put("fozzie","gonzo");
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("assigneeMappingTable",assigneeMappingTable);
  runtimeService.startProcessInstanceByKey("customTaskAssignment",variables);
  assertNotNull(taskService.createTaskQuery().taskAssignee("gonzo").singleResult());
  assertEquals(0,taskService.createTaskQuery().taskAssignee("fozzie").count());
  assertEquals(0,taskService.createTaskQuery().taskAssignee("kermit").count());
}
