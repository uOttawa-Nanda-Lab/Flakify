/** 
 * @see <a href="https://activiti.atlassian.net/browse/ACT-1847">https://activiti.atlassian.net/browse/ACT-1847</a>
 */
@Deployment public void testDataObjectScope(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("dataObjectScope");
  Task currentTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Complete Task A",currentTask.getName());
  Map<String,Object> variables=runtimeService.getVariables(pi.getId());
  assertEquals(2,variables.size());
  Iterator<String> varNameIt=variables.keySet().iterator();
  while (varNameIt.hasNext()) {
    String varName=varNameIt.next();
    if ("StringTest123".equals(varName)) {
      assertEquals("Testing123",variables.get(varName));
    }
 else     if ("NoData123".equals(varName)) {
      assertNull(variables.get(varName));
    }
 else {
      fail("Variable not expected " + varName);
    }
  }
  taskService.complete(currentTask.getId());
  currentTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Complete SubTask",currentTask.getName());
  variables=runtimeService.getVariables(currentTask.getExecutionId());
  assertEquals(3,variables.size());
  varNameIt=variables.keySet().iterator();
  while (varNameIt.hasNext()) {
    String varName=varNameIt.next();
    if ("StringTest123".equals(varName)) {
      assertEquals("Testing123",variables.get(varName));
    }
 else     if ("StringTest456".equals(varName)) {
      assertEquals("Testing456",variables.get(varName));
    }
 else     if ("NoData123".equals(varName)) {
      assertNull(variables.get(varName));
    }
 else {
      fail("Variable not expected " + varName);
    }
  }
  taskService.complete(currentTask.getId());
  variables=runtimeService.getVariables(pi.getId());
  assertEquals(2,variables.size());
  varNameIt=variables.keySet().iterator();
  while (varNameIt.hasNext()) {
    String varName=varNameIt.next();
    if ("StringTest123".equals(varName)) {
      assertEquals("Testing123",variables.get(varName));
    }
 else     if ("NoData123".equals(varName)) {
      assertNull(variables.get(varName));
    }
 else {
      fail("Variable not expected " + varName);
    }
  }
  currentTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  variables=runtimeService.getVariablesLocal(currentTask.getExecutionId());
  assertEquals(0,variables.size());
  currentTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Complete Task B",currentTask.getName());
  taskService.complete(currentTask.getId());
  assertNull(runtimeService.createProcessInstanceQuery().processInstanceId(pi.getId()).singleResult());
}
