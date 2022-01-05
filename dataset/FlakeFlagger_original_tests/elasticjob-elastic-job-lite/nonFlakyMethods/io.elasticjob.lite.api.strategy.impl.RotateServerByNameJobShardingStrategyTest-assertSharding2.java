@Test public void assertSharding2(){
  Map<JobInstance,List<Integer>> expected=new HashMap<>();
  expected.put(new JobInstance("host2@-@0"),Collections.singletonList(0));
  expected.put(new JobInstance("host0@-@0"),Collections.singletonList(1));
  expected.put(new JobInstance("host1@-@0"),Collections.<Integer>emptyList());
  assertThat(rotateServerByNameJobShardingStrategy.sharding(Arrays.asList(new JobInstance("host0@-@0"),new JobInstance("host1@-@0"),new JobInstance("host2@-@0")),"2",2),is(expected));
}
