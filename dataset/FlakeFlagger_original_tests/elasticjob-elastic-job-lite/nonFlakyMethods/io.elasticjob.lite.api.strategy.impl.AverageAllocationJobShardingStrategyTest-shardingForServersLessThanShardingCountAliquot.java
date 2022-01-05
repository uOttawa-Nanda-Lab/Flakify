@Test public void shardingForServersLessThanShardingCountAliquot(){
  Map<JobInstance,List<Integer>> expected=new LinkedHashMap<>(3,1);
  expected.put(new JobInstance("host0@-@0"),Arrays.asList(0,1,2));
  expected.put(new JobInstance("host1@-@0"),Arrays.asList(3,4,5));
  expected.put(new JobInstance("host2@-@0"),Arrays.asList(6,7,8));
  assertThat(jobShardingStrategy.sharding(Arrays.asList(new JobInstance("host0@-@0"),new JobInstance("host1@-@0"),new JobInstance("host2@-@0")),"test_job",9),is(expected));
}
