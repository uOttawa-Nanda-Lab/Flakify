@Test public void wrap() throws Exception {
  XADataSource dataSource=mock(XADataSource.class);
  AtomikosXADataSourceWrapper wrapper=new AtomikosXADataSourceWrapper();
  DataSource wrapped=wrapper.wrapDataSource(dataSource);
  assertThat(wrapped,instanceOf(AtomikosDataSourceBean.class));
  assertThat(((AtomikosDataSourceBean)wrapped).getXaDataSource(),sameInstance(dataSource));
}
