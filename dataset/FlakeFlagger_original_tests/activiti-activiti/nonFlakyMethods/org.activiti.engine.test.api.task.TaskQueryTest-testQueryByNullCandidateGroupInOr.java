public void testQueryByNullCandidateGroupInOr(){
  try {
    taskService.createTaskQuery().or().taskId("invalid").taskCandidateGroupIn(null).list();
    fail("expected exception");
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
  try {
    taskService.createTaskQuery().or().taskId("invalid").taskCandidateGroupIn(new ArrayList<String>()).list();
    fail("expected exception");
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
