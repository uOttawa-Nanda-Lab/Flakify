@Test @WithUserDetails(value="garth",userDetailsServiceBeanName="myUserDetailsService") public void bCreateStandaloneTaskForGroup(){
  Task standAloneTask=taskRuntime.create(TaskPayloadBuilder.create().withName("find Lucien Sanchez").withGroup("doctor").build());
  Page<Task> tasks=taskRuntime.tasks(Pageable.of(0,50));
  assertThat(tasks.getContent()).hasSize(1);
  Task task=tasks.getContent().get(0);
  assertThat(task.getAssignee()).isNull();
  assertThat(task.getStatus()).isEqualTo(Task.TaskStatus.CREATED);
  Task claimedTask=taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(task.getId()).build());
  assertThat(claimedTask.getAssignee()).isEqualTo(securityManager.getAuthenticatedUserId());
  assertThat(claimedTask.getStatus()).isEqualTo(Task.TaskStatus.ASSIGNED);
  Task deletedTask=taskRuntime.delete(TaskPayloadBuilder.delete().withTaskId(task.getId()).withReason("test clean up").build());
  assertThat(deletedTask).isNotNull();
  assertThat(deletedTask.getStatus()).isEqualTo(Task.TaskStatus.DELETED);
  tasks=taskRuntime.tasks(Pageable.of(0,50));
  assertThat(tasks.getContent()).hasSize(0);
}
