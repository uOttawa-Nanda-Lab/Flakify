public void testQueryByNullCandidateUserOr(){
  try {
    taskService.createTaskQuery().or().taskId("invalid").taskCandidateUser(null,null).list();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
