@Test public void should_remove_counter() throws Exception {
  long version=154321L;
  bean=CompleteBeanTestBuilder.builder().randomId().name("test").buid();
  bean=manager.persist(bean);
  bean.getVersion().incr(version);
  manager.update(bean);
  Row row=session.execute("select counter_value from achilles_counter_table where fqcn='" + CompleteBean.class.getCanonicalName() + "' and primary_key='"+ bean.getId()+ "' and property_name='version'").one();
  assertThat(row.getLong("counter_value")).isEqualTo(version);
  Thread.sleep(1000);
  manager.remove(bean);
  row=session.execute("select counter_value from achilles_counter_table where fqcn='" + CompleteBean.class.getCanonicalName() + "' and primary_key='"+ bean.getId()+ "' and property_name='version'").one();
  assertThat(row).isNull();
}
