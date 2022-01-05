public void testQueryByNullCandidateGroup(){
  try {
    taskService.createTaskQuery().taskCandidateGroup(null).list();
    fail("expected exception");
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
