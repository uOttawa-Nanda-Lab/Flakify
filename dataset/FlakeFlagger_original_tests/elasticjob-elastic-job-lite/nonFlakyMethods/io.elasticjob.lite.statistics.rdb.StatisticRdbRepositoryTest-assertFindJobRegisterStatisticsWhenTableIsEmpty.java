@Test public void assertFindJobRegisterStatisticsWhenTableIsEmpty(){
  assertThat(repository.findJobRegisterStatistics(new Date()).size(),is(0));
}
