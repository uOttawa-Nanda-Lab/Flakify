@Test public void testGetStagesInProgress(){
  String hostname="host1";
  populateActionDB(db,hostname,requestId,stageId);
  populateActionDB(db,hostname,requestId,stageId + 1);
  List<Stage> stages=db.getStagesInProgress();
  assertEquals(2,stages.size());
}
