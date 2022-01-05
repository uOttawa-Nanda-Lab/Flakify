@Test public void should_dirty_check_set_remove() throws Exception {
  bean.getFollowers().remove("Sylvain");
  bean.getFollowers().removeAll(asList("George"));
  manager.update(bean);
  Row row=session.execute("select followers from CompleteBean where id=" + bean.getId()).one();
  Set<String> friends=row.getSet("followers",String.class);
  assertThat(friends).containsOnly("Paul");
}
