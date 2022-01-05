@Test @WithUserDetails(value="garth",userDetailsServiceBeanName="myUserDetailsService") public void cCreateStandaloneTaskForGroupAndClaim(){
  String authenticatedUserId=securityManager.getAuthenticatedUserId();
  Task standAloneTask=taskRuntime.create(TaskPayloadBuilder.create().withName("group task").withGroup("doctor").build());
  Page<Task> tasks=taskRuntime.tasks(Pageable.of(0,50));
  assertThat(tasks.getContent()).hasSize(1);
  Task task=tasks.getContent().get(0);
  assertThat(task.getAssignee()).isNull();
  assertThat(task.getStatus()).isEqualTo(Task.TaskStatus.CREATED);
  Task claimedTask=taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(task.getId()).build());
  assertThat(claimedTask.getAssignee()).isEqualTo(authenticatedUserId);
  assertThat(claimedTask.getStatus()).isEqualTo(Task.TaskStatus.ASSIGNED);
}
