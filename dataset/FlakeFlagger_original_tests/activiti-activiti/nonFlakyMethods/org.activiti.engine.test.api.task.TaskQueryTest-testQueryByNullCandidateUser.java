public void testQueryByNullCandidateUser(){
  try {
    taskService.createTaskQuery().taskCandidateUser(null,null).list();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
