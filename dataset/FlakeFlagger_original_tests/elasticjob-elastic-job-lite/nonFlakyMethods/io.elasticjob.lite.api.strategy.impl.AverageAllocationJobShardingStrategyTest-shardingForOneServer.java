@Test public void shardingForOneServer(){
  Map<JobInstance,List<Integer>> expected=new LinkedHashMap<>(1,1);
  expected.put(new JobInstance("host0@-@0"),Arrays.asList(0,1,2));
  assertThat(jobShardingStrategy.sharding(Collections.singletonList(new JobInstance("host0@-@0")),"test_job",3),is(expected));
}
