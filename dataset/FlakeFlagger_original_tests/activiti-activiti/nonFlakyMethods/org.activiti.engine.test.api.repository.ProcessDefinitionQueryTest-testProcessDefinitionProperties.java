public void testProcessDefinitionProperties(){
  List<ProcessDefinition> processDefinitions=repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionName().asc().orderByProcessDefinitionVersion().asc().orderByProcessDefinitionCategory().asc().list();
  ProcessDefinition processDefinition=processDefinitions.get(0);
  assertEquals("one",processDefinition.getKey());
  assertEquals("One",processDefinition.getName());
  assertTrue(processDefinition.getId().startsWith("one:1"));
  assertEquals("Examples",processDefinition.getCategory());
  processDefinition=processDefinitions.get(1);
  assertEquals("one",processDefinition.getKey());
  assertEquals("One",processDefinition.getName());
  assertTrue(processDefinition.getId().startsWith("one:2"));
  assertEquals("Examples",processDefinition.getCategory());
  processDefinition=processDefinitions.get(2);
  assertEquals("two",processDefinition.getKey());
  assertEquals("Two",processDefinition.getName());
  assertTrue(processDefinition.getId().startsWith("two:1"));
  assertEquals("Examples2",processDefinition.getCategory());
}
