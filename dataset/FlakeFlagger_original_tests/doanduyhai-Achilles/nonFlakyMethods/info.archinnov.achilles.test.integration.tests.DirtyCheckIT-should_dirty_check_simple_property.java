@Test public void should_dirty_check_simple_property() throws Exception {
  bean.setName("another_name");
  manager.update(bean);
  Row row=session.execute("select name from CompleteBean where id=" + bean.getId()).one();
  Object reloadedName=row.getString("name");
  assertThat(reloadedName).isEqualTo("another_name");
}
