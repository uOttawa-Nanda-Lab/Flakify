@Test public void shardingForZeroServer(){
  assertThat(jobShardingStrategy.sharding(Collections.<JobInstance>emptyList(),"test_job",3),is(Collections.<JobInstance,List<Integer>>emptyMap()));
}
