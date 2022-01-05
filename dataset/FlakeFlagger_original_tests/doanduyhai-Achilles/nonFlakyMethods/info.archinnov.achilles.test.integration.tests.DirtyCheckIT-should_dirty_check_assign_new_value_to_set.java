@Test public void should_dirty_check_assign_new_value_to_set() throws Exception {
  bean.setFollowers(newHashSet("Sylvain","Jonathan"));
  manager.update(bean);
  Row row=session.execute("select followers from CompleteBean where id=" + bean.getId()).one();
  Set<String> followers=row.getSet("followers",String.class);
  assertThat(followers).containsOnly("Sylvain","Jonathan");
}
