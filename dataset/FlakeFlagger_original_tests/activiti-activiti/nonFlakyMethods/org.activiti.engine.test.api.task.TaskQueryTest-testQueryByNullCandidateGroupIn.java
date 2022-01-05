public void testQueryByNullCandidateGroupIn(){
  try {
    taskService.createTaskQuery().taskCandidateGroupIn(null).list();
    fail("expected exception");
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
  try {
    taskService.createTaskQuery().taskCandidateGroupIn(new ArrayList<String>()).list();
    fail("expected exception");
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
