public void testBaseProperties(){
  ProcessInstanceHistoryLog log=historyService.createProcessInstanceHistoryLogQuery(processInstanceId).singleResult();
  assertNotNull(log.getId());
  assertNotNull(log.getProcessDefinitionId());
  assertNotNull(log.getStartActivityId());
  assertNotNull(log.getDurationInMillis());
  assertNotNull(log.getEndTime());
  assertNotNull(log.getStartTime());
}
