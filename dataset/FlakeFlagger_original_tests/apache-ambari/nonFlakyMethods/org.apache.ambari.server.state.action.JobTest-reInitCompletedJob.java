@Test public void reInitCompletedJob() throws Exception {
  Action job=getCompletedJob(1,"JobNameFoo",1,false);
  ActionId jId=new ActionId(2,new ActionType("JobNameFoo"));
  ActionInitEvent e=new ActionInitEvent(jId,100);
  job.handleEvent(e);
  Assert.assertEquals(ActionState.INIT,job.getState());
  Assert.assertEquals(100,job.getStartTime());
  Assert.assertEquals(-1,job.getLastUpdateTime());
  Assert.assertEquals(-1,job.getCompletionTime());
  Assert.assertEquals(2,job.getId().actionId);
}
