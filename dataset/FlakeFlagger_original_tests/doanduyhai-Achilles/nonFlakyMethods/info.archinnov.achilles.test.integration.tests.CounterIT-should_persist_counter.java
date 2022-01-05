@Test public void should_persist_counter() throws Exception {
  bean=CompleteBeanTestBuilder.builder().randomId().name("test").version(CounterBuilder.incr(2L)).buid();
  bean=manager.persist(bean);
  Row row=session.execute("select counter_value from achilles_counter_table where fqcn='" + CompleteBean.class.getCanonicalName() + "' and primary_key='"+ bean.getId()+ "' and property_name='version'").one();
  assertThat(row.getLong("counter_value")).isEqualTo(2L);
}
