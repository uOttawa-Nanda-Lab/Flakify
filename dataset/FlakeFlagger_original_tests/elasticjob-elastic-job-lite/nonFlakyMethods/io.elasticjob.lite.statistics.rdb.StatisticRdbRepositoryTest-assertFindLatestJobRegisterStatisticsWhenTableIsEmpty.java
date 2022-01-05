@Test public void assertFindLatestJobRegisterStatisticsWhenTableIsEmpty(){
  assertFalse(repository.findLatestJobRegisterStatistics().isPresent());
}
