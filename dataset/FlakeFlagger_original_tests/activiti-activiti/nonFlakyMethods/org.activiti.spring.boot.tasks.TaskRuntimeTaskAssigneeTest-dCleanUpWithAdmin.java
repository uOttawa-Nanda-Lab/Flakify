@Test @WithUserDetails(value="admin",userDetailsServiceBeanName="myUserDetailsService") public void dCleanUpWithAdmin(){
  Page<Task> tasks=taskAdminRuntime.tasks(Pageable.of(0,50));
  for (  Task t : tasks.getContent()) {
    taskAdminRuntime.delete(TaskPayloadBuilder.delete().withTaskId(t.getId()).withReason("test clean up").build());
  }
}
