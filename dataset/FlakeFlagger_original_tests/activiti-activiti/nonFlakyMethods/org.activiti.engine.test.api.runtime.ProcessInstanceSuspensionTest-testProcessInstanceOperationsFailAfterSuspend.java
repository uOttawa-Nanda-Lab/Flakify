@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testProcessInstanceOperationsFailAfterSuspend(){
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  ProcessInstance processInstance=runtimeService.startProcessInstanceById(processDefinition.getId());
  runtimeService.suspendProcessInstanceById(processInstance.getId());
  try {
    runtimeService.messageEventReceived("someMessage",processInstance.getId());
    fail();
  }
 catch (  ActivitiException e) {
    e.getMessage().contains("is suspended");
  }
  try {
    runtimeService.messageEventReceived("someMessage",processInstance.getId(),new HashMap<String,Object>());
    fail();
  }
 catch (  ActivitiException e) {
    e.getMessage().contains("is suspended");
  }
  try {
    runtimeService.removeVariable(processInstance.getId(),"someVariable");
    fail();
  }
 catch (  ActivitiException e) {
    e.getMessage().contains("is suspended");
  }
  try {
    runtimeService.removeVariableLocal(processInstance.getId(),"someVariable");
    fail();
  }
 catch (  ActivitiException e) {
    e.getMessage().contains("is suspended");
  }
  try {
    runtimeService.removeVariables(processInstance.getId(),Arrays.asList("one","two","three"));
    fail();
  }
 catch (  ActivitiException e) {
    e.getMessage().contains("is suspended");
  }
  try {
    runtimeService.removeVariablesLocal(processInstance.getId(),Arrays.asList("one","two","three"));
    fail();
  }
 catch (  ActivitiException e) {
    e.getMessage().contains("is suspended");
  }
  try {
    runtimeService.setVariable(processInstance.getId(),"someVariable","someValue");
    fail();
  }
 catch (  ActivitiException e) {
    e.getMessage().contains("is suspended");
  }
  try {
    runtimeService.setVariableLocal(processInstance.getId(),"someVariable","someValue");
    fail();
  }
 catch (  ActivitiException e) {
    e.getMessage().contains("is suspended");
  }
  try {
    runtimeService.setVariables(processInstance.getId(),new HashMap<String,Object>());
    fail();
  }
 catch (  ActivitiException e) {
    e.getMessage().contains("is suspended");
  }
  try {
    runtimeService.setVariablesLocal(processInstance.getId(),new HashMap<String,Object>());
    fail();
  }
 catch (  ActivitiException e) {
    e.getMessage().contains("is suspended");
  }
  try {
    runtimeService.trigger(processInstance.getId());
    fail();
  }
 catch (  ActivitiException e) {
    e.getMessage().contains("is suspended");
  }
  try {
    runtimeService.trigger(processInstance.getId(),new HashMap<String,Object>());
    fail();
  }
 catch (  ActivitiException e) {
    e.getMessage().contains("is suspended");
  }
  try {
    runtimeService.signalEventReceived("someSignal",processInstance.getId());
    fail();
  }
 catch (  ActivitiException e) {
    e.getMessage().contains("is suspended");
  }
  try {
    runtimeService.signalEventReceived("someSignal",processInstance.getId(),new HashMap<String,Object>());
    fail();
  }
 catch (  ActivitiException e) {
    e.getMessage().contains("is suspended");
  }
}
