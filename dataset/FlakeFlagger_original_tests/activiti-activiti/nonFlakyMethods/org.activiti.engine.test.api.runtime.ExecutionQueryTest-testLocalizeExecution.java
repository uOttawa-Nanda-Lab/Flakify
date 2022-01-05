@Deployment(resources={"org/activiti/engine/test/api/runtime/executionLocalization.bpmn20.xml"}) public void testLocalizeExecution() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("executionLocalization");
  List<Execution> executions=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).list();
  assertEquals(3,executions.size());
  for (  Execution execution : executions) {
    if (execution.getParentId() == null) {
      assertNull(execution.getName());
      assertNull(execution.getDescription());
    }
 else     if (execution.getParentId().equals(execution.getProcessInstanceId())) {
      assertNull(execution.getName());
      assertNull(execution.getDescription());
    }
  }
  ObjectNode infoNode=dynamicBpmnService.getProcessDefinitionInfo(processInstance.getProcessDefinitionId());
  dynamicBpmnService.changeLocalizationName("en-GB","executionLocalization","Process Name 'en-GB'",infoNode);
  dynamicBpmnService.changeLocalizationDescription("en-GB","executionLocalization","Process Description 'en-GB'",infoNode);
  dynamicBpmnService.saveProcessDefinitionInfo(processInstance.getProcessDefinitionId(),infoNode);
  dynamicBpmnService.changeLocalizationName("en","executionLocalization","Process Name 'en'",infoNode);
  dynamicBpmnService.changeLocalizationDescription("en","executionLocalization","Process Description 'en'",infoNode);
  dynamicBpmnService.changeLocalizationName("en-GB","subProcess","SubProcess Name 'en-GB'",infoNode);
  dynamicBpmnService.changeLocalizationDescription("en-GB","subProcess","SubProcess Description 'en-GB'",infoNode);
  dynamicBpmnService.changeLocalizationName("en","subProcess","SubProcess Name 'en'",infoNode);
  dynamicBpmnService.changeLocalizationDescription("en","subProcess","SubProcess Description 'en'",infoNode);
  dynamicBpmnService.saveProcessDefinitionInfo(processInstance.getProcessDefinitionId(),infoNode);
  executions=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).list();
  String subProcessId=null;
  assertEquals(3,executions.size());
  for (  Execution execution : executions) {
    if (execution.getParentId() == null) {
      assertNull(execution.getName());
      assertNull(execution.getDescription());
    }
 else     if (execution.getParentId().equals(execution.getProcessInstanceId())) {
      assertNull(execution.getName());
      assertNull(execution.getDescription());
      subProcessId=execution.getId();
    }
  }
  executions=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).locale("es").list();
  assertEquals(3,executions.size());
  for (  Execution execution : executions) {
    if (execution.getParentId() == null) {
      assertEquals("Nombre del proceso",execution.getName());
      assertEquals("Descripción del proceso",execution.getDescription());
    }
 else     if (execution.getParentId().equals(execution.getProcessInstanceId())) {
      assertEquals("Nombre Subproceso",execution.getName());
      assertEquals("Subproceso Descripción",execution.getDescription());
    }
  }
  executions=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).locale("en-GB").list();
  assertEquals(3,executions.size());
  for (  Execution execution : executions) {
    if (execution.getParentId() == null) {
      assertEquals("Process Name 'en-GB'",execution.getName());
      assertEquals("Process Description 'en-GB'",execution.getDescription());
    }
 else     if (execution.getParentId().equals(execution.getProcessInstanceId())) {
      assertEquals("SubProcess Name 'en-GB'",execution.getName());
      assertEquals("SubProcess Description 'en-GB'",execution.getDescription());
    }
  }
  executions=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).listPage(0,10);
  assertEquals(3,executions.size());
  for (  Execution execution : executions) {
    if (execution.getParentId() == null) {
      assertNull(execution.getName());
      assertNull(execution.getDescription());
    }
 else     if (execution.getParentId().equals(execution.getProcessInstanceId())) {
      assertNull(execution.getName());
      assertNull(execution.getDescription());
    }
  }
  executions=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).locale("es").listPage(0,10);
  assertEquals(3,executions.size());
  for (  Execution execution : executions) {
    if (execution.getParentId() == null) {
      assertEquals("Nombre del proceso",execution.getName());
      assertEquals("Descripción del proceso",execution.getDescription());
    }
 else     if (execution.getParentId().equals(execution.getProcessInstanceId())) {
      assertEquals("Nombre Subproceso",execution.getName());
      assertEquals("Subproceso Descripción",execution.getDescription());
    }
  }
  executions=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).locale("en-GB").listPage(0,10);
  assertEquals(3,executions.size());
  for (  Execution execution : executions) {
    if (execution.getParentId() == null) {
      assertEquals("Process Name 'en-GB'",execution.getName());
      assertEquals("Process Description 'en-GB'",execution.getDescription());
    }
 else     if (execution.getParentId().equals(execution.getProcessInstanceId())) {
      assertEquals("SubProcess Name 'en-GB'",execution.getName());
      assertEquals("SubProcess Description 'en-GB'",execution.getDescription());
    }
  }
  Execution execution=runtimeService.createExecutionQuery().executionId(processInstance.getId()).singleResult();
  assertNull(execution.getName());
  assertNull(execution.getDescription());
  execution=runtimeService.createExecutionQuery().executionId(subProcessId).singleResult();
  assertNull(execution.getName());
  assertNull(execution.getDescription());
  execution=runtimeService.createExecutionQuery().executionId(processInstance.getId()).locale("es").singleResult();
  assertEquals("Nombre del proceso",execution.getName());
  assertEquals("Descripción del proceso",execution.getDescription());
  execution=runtimeService.createExecutionQuery().executionId(subProcessId).locale("es").singleResult();
  assertEquals("Nombre Subproceso",execution.getName());
  assertEquals("Subproceso Descripción",execution.getDescription());
  execution=runtimeService.createExecutionQuery().executionId(processInstance.getId()).locale("en-GB").singleResult();
  assertEquals("Process Name 'en-GB'",execution.getName());
  assertEquals("Process Description 'en-GB'",execution.getDescription());
  execution=runtimeService.createExecutionQuery().executionId(subProcessId).locale("en-GB").singleResult();
  assertEquals("SubProcess Name 'en-GB'",execution.getName());
  assertEquals("SubProcess Description 'en-GB'",execution.getDescription());
  execution=runtimeService.createExecutionQuery().executionId(processInstance.getId()).locale("en-AU").withLocalizationFallback().singleResult();
  assertEquals("Process Name 'en'",execution.getName());
  assertEquals("Process Description 'en'",execution.getDescription());
  execution=runtimeService.createExecutionQuery().executionId(subProcessId).locale("en-AU").withLocalizationFallback().singleResult();
  assertEquals("SubProcess Name 'en'",execution.getName());
  assertEquals("SubProcess Description 'en'",execution.getDescription());
  dynamicBpmnService.changeLocalizationName("en-US","executionLocalization","Process Name 'en-US'",infoNode);
  dynamicBpmnService.changeLocalizationDescription("en-US","executionLocalization","Process Description 'en-US'",infoNode);
  dynamicBpmnService.saveProcessDefinitionInfo(processInstance.getProcessDefinitionId(),infoNode);
  dynamicBpmnService.changeLocalizationName("en-US","subProcess","SubProcess Name 'en-US'",infoNode);
  dynamicBpmnService.changeLocalizationDescription("en-US","subProcess","SubProcess Description 'en-US'",infoNode);
  dynamicBpmnService.saveProcessDefinitionInfo(processInstance.getProcessDefinitionId(),infoNode);
  execution=runtimeService.createExecutionQuery().executionId(processInstance.getId()).locale("en-US").singleResult();
  assertEquals("Process Name 'en-US'",execution.getName());
  assertEquals("Process Description 'en-US'",execution.getDescription());
  execution=runtimeService.createExecutionQuery().executionId(subProcessId).locale("en-US").singleResult();
  assertEquals("SubProcess Name 'en-US'",execution.getName());
  assertEquals("SubProcess Description 'en-US'",execution.getDescription());
}
