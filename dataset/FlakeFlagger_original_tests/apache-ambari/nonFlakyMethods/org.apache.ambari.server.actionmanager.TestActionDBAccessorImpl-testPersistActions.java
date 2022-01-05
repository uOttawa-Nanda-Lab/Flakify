@Test public void testPersistActions(){
  populateActionDB(db,hostName,requestId,stageId);
  for (  Stage stage : db.getAllStages(requestId)) {
    log.info("taskId={}" + stage.getExecutionCommands(hostName).get(0).getExecutionCommand().getTaskId());
    assertTrue(stage.getExecutionCommands(hostName).get(0).getExecutionCommand().getTaskId() > 0);
    assertTrue(executionCommandDAO.findByPK(stage.getExecutionCommands(hostName).get(0).getExecutionCommand().getTaskId()) != null);
  }
}
