@Test @WithUserDetails(value="garth",userDetailsServiceBeanName="myUserDetailsService") public void cCreateStandaloneTaskReleaseUnAuthorized(){
  Task standAloneTask=taskRuntime.create(TaskPayloadBuilder.create().withName("group task").withGroup("activitiTeam").build());
  assertThat(standAloneTask.getAssignee()).isNull();
  assertThat(standAloneTask.getStatus()).isEqualTo(Task.TaskStatus.CREATED);
  Throwable thrown=catchThrowable(() -> {
    taskRuntime.release(TaskPayloadBuilder.release().withTaskId(standAloneTask.getId()).build());
  }
);
  assertThat(thrown).isInstanceOf(IllegalStateException.class);
}
