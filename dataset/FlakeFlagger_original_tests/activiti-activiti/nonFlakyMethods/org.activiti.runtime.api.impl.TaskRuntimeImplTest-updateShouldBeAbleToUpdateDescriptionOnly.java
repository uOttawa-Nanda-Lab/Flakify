@Test public void updateShouldBeAbleToUpdateDescriptionOnly(){
  UpdateTaskPayload updateTaskPayload=TaskPayloadBuilder.update().withTaskId("taskId").withDescription("new description").build();
  TaskImpl task=new TaskImpl();
  task.setAssignee("user");
  doReturn(task).when(taskRuntime).task("taskId");
  TaskQuery taskQuery=mock(TaskQuery.class);
  given(taskQuery.taskId("taskId")).willReturn(taskQuery);
  given(taskService.createTaskQuery()).willReturn(taskQuery);
  Task internalTask=mock(Task.class);
  given(taskQuery.singleResult()).willReturn(internalTask);
  taskRuntime.update(updateTaskPayload);
  verify(internalTask).setDescription("new description");
  verifyNoMoreInteractions(internalTask);
}
