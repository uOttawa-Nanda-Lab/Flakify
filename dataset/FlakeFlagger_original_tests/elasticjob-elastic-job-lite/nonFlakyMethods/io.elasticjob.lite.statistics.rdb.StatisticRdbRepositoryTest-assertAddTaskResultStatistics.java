@Test public void assertAddTaskResultStatistics(){
  for (  StatisticInterval each : StatisticInterval.values()) {
    assertTrue(repository.add(new TaskResultStatistics(100,0,each,new Date())));
  }
}
