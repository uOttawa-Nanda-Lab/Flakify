@Test public void should_dirty_check_set_element_add() throws Exception {
  bean.getFollowers().add("Jonathan");
  bean.getFollowers().addAll(asList("Sylvain","Mickaël","Jonathan"));
  manager.update(bean);
  Row row=session.execute("select followers from CompleteBean where id=" + bean.getId()).one();
  Set<String> friends=row.getSet("followers",String.class);
  assertThat(friends).containsOnly("George","Paul","Jonathan","Sylvain","Mickaël");
}
