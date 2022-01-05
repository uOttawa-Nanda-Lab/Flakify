@Test @WithUserDetails(value="salaboy",userDetailsServiceBeanName="myUserDetailsService") public void bCreateCheckTaskCreatedForSalaboyFromAnotherUser(){
  Page<Task> tasks=taskRuntime.tasks(Pageable.of(0,50));
  assertThat(tasks.getContent()).hasSize(1);
  Task task=tasks.getContent().get(0);
  String authenticatedUserId=securityManager.getAuthenticatedUserId();
  assertThat(task.getAssignee()).isEqualTo(authenticatedUserId);
  assertThat(task.getStatus()).isEqualTo(Task.TaskStatus.ASSIGNED);
  Task deletedTask=taskRuntime.delete(TaskPayloadBuilder.delete().withTaskId(task.getId()).withReason("test clean up").build());
  assertThat(deletedTask).isNotNull();
  assertThat(deletedTask.getStatus()).isEqualTo(Task.TaskStatus.DELETED);
  tasks=taskRuntime.tasks(Pageable.of(0,50));
  assertThat(tasks.getContent()).hasSize(0);
}
