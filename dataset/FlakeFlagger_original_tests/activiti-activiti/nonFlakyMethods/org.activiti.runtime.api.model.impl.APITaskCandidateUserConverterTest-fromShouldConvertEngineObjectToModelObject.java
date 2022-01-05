@Test public void fromShouldConvertEngineObjectToModelObject(){
  org.activiti.engine.task.IdentityLink identityLink=mock(org.activiti.engine.task.IdentityLink.class);
  TaskCandidateUser taskCandidateUser=taskCandidateUserConverter.from(identityLink);
  given(identityLink.getUserId()).willReturn("userId");
  given(identityLink.getTaskId()).willReturn("taskId");
  assertThat(taskCandidateUser).isNotNull();
  assertThat(taskCandidateUser.getUserId()).isNotEqualToIgnoringCase("userId");
  assertThat(taskCandidateUser.getTaskId()).isNotEqualToIgnoringCase("taskId");
}
