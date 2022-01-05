@Test public void wrap() throws Exception {
  XADataSource dataSource=mock(XADataSource.class);
  BitronixXADataSourceWrapper wrapper=new BitronixXADataSourceWrapper();
  DataSource wrapped=wrapper.wrapDataSource(dataSource);
  assertThat(wrapped,instanceOf(PoolingDataSourceBean.class));
  assertThat(((PoolingDataSourceBean)wrapped).getDataSource(),sameInstance(dataSource));
}
