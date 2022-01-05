@Test public void should_dirty_check_set_clear() throws Exception {
  bean.getFollowers().clear();
  bean.getFollowers().addAll(asList("Sylvain","Jonathan"));
  manager.update(bean);
  Row row=session.execute("select followers from CompleteBean where id=" + bean.getId()).one();
  Set<String> friends=row.getSet("followers",String.class);
  assertThat(friends).containsOnly("Jonathan","Sylvain");
}
