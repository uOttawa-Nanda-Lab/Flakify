public void testGetVariables(){
  Map<String,Object> vars=runtimeService.getVariables(processInstanceId);
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
  Task task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  task=taskService.createTaskQuery().taskName("Task 3").singleResult();
  String executionId=task.getExecutionId();
  assertFalse(processInstanceId.equals(executionId));
  vars=runtimeService.getVariables(processInstanceId);
  assertEquals(70,vars.size());
  nrOfStrings=0;
  nrOfInts=0;
  nrOfDates=0;
  nrOfLocalDates=0;
  nrOfDateTimes=0;
  nrOfBooleans=0;
  nrOfSerializable=0;
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
}
