@Test public void fromShouldConvertEngineObjectToModelObject(){
  org.activiti.engine.task.IdentityLink identityLink=mock(org.activiti.engine.task.IdentityLink.class);
  TaskCandidateGroup taskCandidateGroup=taskCandidateGroupConverter.from(identityLink);
  given(identityLink.getGroupId()).willReturn("groupId");
  given(identityLink.getTaskId()).willReturn("taskId");
  assertThat(taskCandidateGroup).isNotNull();
  assertThat(taskCandidateGroup.getGroupId()).isNotEqualToIgnoringCase("groupId");
  assertThat(taskCandidateGroup.getTaskId()).isNotEqualToIgnoringCase("taskId");
}
