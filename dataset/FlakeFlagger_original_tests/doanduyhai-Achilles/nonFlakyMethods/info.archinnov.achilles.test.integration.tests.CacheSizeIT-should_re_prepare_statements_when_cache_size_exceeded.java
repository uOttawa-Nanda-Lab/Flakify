@Test public void should_re_prepare_statements_when_cache_size_exceeded() throws Exception {
  CompleteBean bean=builder().id(RandomUtils.nextLong()).name("name").buid();
  CompleteBean managed=pm.persist(bean);
  managed.setAge(10L);
  pm.update(managed);
  managed.setFriends(Arrays.asList("foo","bar"));
  pm.update(managed);
  managed.setFollowers(Sets.newHashSet("George","Paul"));
  pm.update(managed);
  managed.setAge(11L);
  pm.update(managed);
  CompleteBean found=pm.find(CompleteBean.class,bean.getId());
  assertThat(found.getAge()).isEqualTo(11L);
  assertThat(found.getName()).isEqualTo("name");
  assertThat(found.getFriends()).containsExactly("foo","bar");
  assertThat(found.getFollowers()).containsOnly("George","Paul");
}
