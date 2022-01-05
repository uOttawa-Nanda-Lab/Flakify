public void testDeployIdenticalProcessDefinitions(){
  List<String> deploymentIds=new ArrayList<String>();
  deploymentIds.add(deployProcessString(("<definitions " + NAMESPACE + " "+ TARGET_NAMESPACE+ ">"+ "  <process id='IDR' name='Insurance Damage Report' />"+ "</definitions>")));
  deploymentIds.add(deployProcessString(("<definitions " + NAMESPACE + " "+ TARGET_NAMESPACE+ ">"+ "  <process id='IDR' name='Insurance Damage Report' />"+ "</definitions>")));
  List<ProcessDefinition> processDefinitions=repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionKey().asc().orderByProcessDefinitionVersion().desc().list();
  assertNotNull(processDefinitions);
  assertEquals(2,processDefinitions.size());
  ProcessDefinition processDefinition=processDefinitions.get(0);
  assertEquals("IDR",processDefinition.getKey());
  assertEquals("Insurance Damage Report",processDefinition.getName());
  assertTrue(processDefinition.getId().startsWith("IDR:2"));
  assertEquals(2,processDefinition.getVersion());
  processDefinition=processDefinitions.get(1);
  assertEquals("IDR",processDefinition.getKey());
  assertEquals("Insurance Damage Report",processDefinition.getName());
  assertTrue(processDefinition.getId().startsWith("IDR:1"));
  assertEquals(1,processDefinition.getVersion());
  deleteDeployments(deploymentIds);
}
