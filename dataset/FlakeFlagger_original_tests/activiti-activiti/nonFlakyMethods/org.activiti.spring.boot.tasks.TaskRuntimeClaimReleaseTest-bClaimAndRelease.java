@Test @WithUserDetails(value="salaboy",userDetailsServiceBeanName="myUserDetailsService") public void bClaimAndRelease(){
  String authenticatedUserId=securityManager.getAuthenticatedUserId();
  Task claimedTask=taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(currentTaskId).build());
  assertThat(claimedTask.getAssignee()).isEqualTo(authenticatedUserId);
  assertThat(claimedTask.getStatus()).isEqualTo(Task.TaskStatus.ASSIGNED);
  Task releasedTask=taskRuntime.release(TaskPayloadBuilder.release().withTaskId(claimedTask.getId()).build());
  assertThat(releasedTask.getAssignee()).isNull();
  assertThat(releasedTask.getStatus()).isEqualTo(Task.TaskStatus.CREATED);
}
