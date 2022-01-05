@Test public void updateShouldThrowExceptionWhenAssigneeIsNotSet(){
  UpdateTaskPayload updateTaskPayload=TaskPayloadBuilder.update().withTaskId("taskId").withDescription("new description").build();
  doReturn(new TaskImpl()).when(taskRuntime).task("taskId");
  Throwable throwable=catchThrowable(() -> taskRuntime.update(updateTaskPayload));
  assertThat(throwable).isInstanceOf(IllegalStateException.class).hasMessage("You cannot update a task where you are not the assignee");
}
