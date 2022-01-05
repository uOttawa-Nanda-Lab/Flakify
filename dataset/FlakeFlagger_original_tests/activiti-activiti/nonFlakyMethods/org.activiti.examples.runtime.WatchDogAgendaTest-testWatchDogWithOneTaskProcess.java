@Deployment(resources="org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml") public void testWatchDogWithOneTaskProcess(){
  this.runtimeService.startProcessInstanceByKey("oneTaskProcess");
  Task task=this.taskService.createTaskQuery().singleResult();
  this.taskService.complete(task.getId());
  assertThat(this.runtimeService.createProcessInstanceQuery().count(),is(0L));
}
