@Test public void shardingForServersMoreThanShardingCount(){
  Map<JobInstance,List<Integer>> expected=new LinkedHashMap<>(3,1);
  expected.put(new JobInstance("host0@-@0"),Collections.singletonList(0));
  expected.put(new JobInstance("host1@-@0"),Collections.singletonList(1));
  expected.put(new JobInstance("host2@-@0"),Collections.<Integer>emptyList());
  assertThat(jobShardingStrategy.sharding(Arrays.asList(new JobInstance("host0@-@0"),new JobInstance("host1@-@0"),new JobInstance("host2@-@0")),"test_job",2),is(expected));
}
