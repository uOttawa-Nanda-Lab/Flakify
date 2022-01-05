@Test public void testGetStagesInProgressWithFailures(){
  String hostname="host1";
  populateActionDB(db,hostname,requestId,stageId);
  populateActionDB(db,hostname,requestId + 1,stageId);
  db.abortOperation(requestId);
  List<Stage> stages=db.getStagesInProgress();
  assertEquals(1,stages.size());
  assertEquals(requestId + 1,stages.get(0).getRequestId());
}
