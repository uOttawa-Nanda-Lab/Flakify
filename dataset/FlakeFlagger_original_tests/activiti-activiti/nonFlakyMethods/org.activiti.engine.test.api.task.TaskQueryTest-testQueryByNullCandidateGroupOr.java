public void testQueryByNullCandidateGroupOr(){
  try {
    taskService.createTaskQuery().or().taskId("invalid").taskCandidateGroup(null).list();
    fail("expected exception");
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
