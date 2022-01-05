@Test public void shardingForServersLessThanShardingCountAliquantFor8ShardingCountAnd3Servers(){
  Map<JobInstance,List<Integer>> expected=new LinkedHashMap<>(3,1);
  expected.put(new JobInstance("host0@-@0"),Arrays.asList(0,1,6));
  expected.put(new JobInstance("host1@-@0"),Arrays.asList(2,3,7));
  expected.put(new JobInstance("host2@-@0"),Arrays.asList(4,5));
  assertThat(jobShardingStrategy.sharding(Arrays.asList(new JobInstance("host0@-@0"),new JobInstance("host1@-@0"),new JobInstance("host2@-@0")),"test_job",8),is(expected));
}
