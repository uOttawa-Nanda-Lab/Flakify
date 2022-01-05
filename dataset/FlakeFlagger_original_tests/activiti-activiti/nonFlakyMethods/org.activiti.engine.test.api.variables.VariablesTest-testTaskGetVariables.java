public void testTaskGetVariables(){
  Task task=taskService.createTaskQuery().taskName("Task 1").singleResult();
  Map<String,Object> vars=taskService.getVariables(task.getId());
  assertEquals(70,vars.size());
  int nrOfStrings=0, nrOfInts=0, nrOfDates=0, nrOfLocalDates=0, nrOfDateTimes=0, nrOfBooleans=0, nrOfSerializable=0;
  for (  String variableName : vars.keySet()) {
    Object variableValue=vars.get(variableName);
    if (variableValue instanceof String) {
      nrOfStrings++;
    }
 else     if (variableValue instanceof Integer) {
      nrOfInts++;
    }
 else     if (variableValue instanceof Boolean) {
      nrOfBooleans++;
    }
 else     if (variableValue instanceof Date) {
      nrOfDates++;
    }
 else     if (variableValue instanceof LocalDate) {
      nrOfLocalDates++;
    }
 else     if (variableValue instanceof DateTime) {
      nrOfDateTimes++;
    }
 else     if (variableValue instanceof TestSerializableVariable) {
      nrOfSerializable++;
    }
  }
  assertEquals(10,nrOfStrings);
  assertEquals(10,nrOfBooleans);
  assertEquals(10,nrOfDates);
  assertEquals(10,nrOfLocalDates);
  assertEquals(10,nrOfDateTimes);
  assertEquals(10,nrOfInts);
  assertEquals(10,nrOfSerializable);
  assertEquals(0,taskService.getVariablesLocal(task.getId()).size());
  assertEquals(2,taskService.getVariables(task.getId(),Arrays.asList("intVar2","intVar5")).size());
  assertEquals(0,taskService.getVariablesLocal(task.getId(),Arrays.asList("intVar2","intVar5")).size());
  assertEquals("stringVarValue-3",taskService.getVariable(task.getId(),"stringVar3"));
  assertNull(taskService.getVariable(task.getId(),"stringVarDoesNotExist"));
  assertNull(taskService.getVariableLocal(task.getId(),"stringVar3"));
  taskService.setVariableLocal(task.getId(),"localTaskVar","localTaskVarValue");
  assertEquals(71,taskService.getVariables(task.getId()).size());
  assertEquals(1,taskService.getVariablesLocal(task.getId()).size());
  assertEquals(2,taskService.getVariables(task.getId(),Arrays.asList("intVar2","intVar5")).size());
  assertEquals(0,taskService.getVariablesLocal(task.getId(),Arrays.asList("intVar2","intVar5")).size());
  assertEquals("localTaskVarValue",taskService.getVariable(task.getId(),"localTaskVar"));
  assertEquals("localTaskVarValue",taskService.getVariableLocal(task.getId(),"localTaskVar"));
  Collection<String> varNames=new ArrayList<String>();
  varNames.add("stringVar1");
  assertEquals("stringVarValue-1",taskService.getVariable(task.getId(),"stringVar1"));
  assertEquals("stringVarValue-1",taskService.getVariable(task.getId(),"stringVar1"));
  assertEquals("stringVarValue-1",taskService.getVariables(task.getId(),varNames).get("stringVar1"));
  taskService.setVariableLocal(task.getId(),"stringVar1","Override");
  assertEquals(71,taskService.getVariables(task.getId()).size());
  assertEquals("Override",taskService.getVariable(task.getId(),"stringVar1"));
  assertEquals("Override",taskService.getVariables(task.getId(),varNames).get("stringVar1"));
}
