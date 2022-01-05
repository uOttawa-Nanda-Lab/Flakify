/** 
 * @see <a href="https://activiti.atlassian.net/browse/ACT-380">https://activiti.atlassian.net/browse/ACT-380</a>
 */
@Deployment public void testGetCandidates(){
  runtimeService.startProcessInstanceByKey("DelegateTaskTest.testGetCandidates");
  Task task=taskService.createTaskQuery().singleResult();
  assertNotNull(task);
  @SuppressWarnings("unchecked") Set<String> candidateUsers=(Set<String>)taskService.getVariable(task.getId(),DelegateTaskTestTaskListener.VARNAME_CANDIDATE_USERS);
  assertEquals(2,candidateUsers.size());
  assertTrue(candidateUsers.contains("kermit"));
  assertTrue(candidateUsers.contains("gonzo"));
  @SuppressWarnings("unchecked") Set<String> candidateGroups=(Set<String>)taskService.getVariable(task.getId(),DelegateTaskTestTaskListener.VARNAME_CANDIDATE_GROUPS);
  assertEquals(2,candidateGroups.size());
  assertTrue(candidateGroups.contains("management"));
  assertTrue(candidateGroups.contains("accountancy"));
}
