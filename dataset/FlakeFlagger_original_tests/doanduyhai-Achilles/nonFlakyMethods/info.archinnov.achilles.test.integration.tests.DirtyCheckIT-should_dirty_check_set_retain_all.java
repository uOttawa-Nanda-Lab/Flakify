@Test public void should_dirty_check_set_retain_all() throws Exception {
  bean.getFollowers().retainAll(asList("Sylvain","Paul"));
  manager.update(bean);
  Row row=session.execute("select followers from CompleteBean where id=" + bean.getId()).one();
  Set<String> friends=row.getSet("followers",String.class);
  assertThat(friends).containsOnly("Paul");
}
