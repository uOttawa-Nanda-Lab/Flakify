public void testQueryInvalidSortingUsage(){
  try {
    managementService.createJobQuery().orderByJobId().list();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
    assertTextPresent("call asc() or desc() after using orderByXX()",e.getMessage());
  }
  try {
    managementService.createJobQuery().asc();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
    assertTextPresent("You should call any of the orderBy methods first before specifying a direction",e.getMessage());
  }
}
