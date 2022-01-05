@Test @WithUserDetails(value="garth",userDetailsServiceBeanName="myUserDetailsService") public void aCreateStandaloneTaskForAnotherAssignee(){
  taskRuntime.create(TaskPayloadBuilder.create().withName("task for salaboy").withAssignee("salaboy").build());
  Page<Task> tasks=taskRuntime.tasks(Pageable.of(0,50));
  assertThat(tasks.getContent()).hasSize(1);
  Task task=tasks.getContent().get(0);
  assertThat(task.getAssignee()).isEqualTo("salaboy");
  assertThat(task.getStatus()).isEqualTo(Task.TaskStatus.ASSIGNED);
}
