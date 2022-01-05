@Test @WithUserDetails(value="garth",userDetailsServiceBeanName="myUserDetailsService") public void aCreateStandaloneTaskAndComplete(){
  String authenticatedUserId=securityManager.getAuthenticatedUserId();
  Task standAloneTask=taskRuntime.create(TaskPayloadBuilder.create().withName("simple task").withAssignee(authenticatedUserId).build());
  Page<Task> tasks=taskRuntime.tasks(Pageable.of(0,50));
  assertThat(tasks.getContent()).hasSize(1);
  Task task=tasks.getContent().get(0);
  assertThat(task.getAssignee()).isEqualTo(authenticatedUserId);
  assertThat(task.getStatus()).isEqualTo(Task.TaskStatus.ASSIGNED);
  Task completedTask=taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(task.getId()).build());
  assertThat(completedTask.getStatus()).isEqualTo(Task.TaskStatus.COMPLETED);
}
