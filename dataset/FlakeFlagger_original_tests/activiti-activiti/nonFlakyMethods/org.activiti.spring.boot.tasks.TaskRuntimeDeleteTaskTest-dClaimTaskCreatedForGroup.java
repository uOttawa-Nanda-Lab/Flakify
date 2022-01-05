@Test @WithUserDetails(value="salaboy",userDetailsServiceBeanName="myUserDetailsService") public void dClaimTaskCreatedForGroup(){
  String authenticatedUserId=securityManager.getAuthenticatedUserId();
  Task claimedTask=taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(currentTaskId).build());
  assertThat(claimedTask.getAssignee()).isEqualTo(authenticatedUserId);
  assertThat(claimedTask.getStatus()).isEqualTo(Task.TaskStatus.ASSIGNED);
}
