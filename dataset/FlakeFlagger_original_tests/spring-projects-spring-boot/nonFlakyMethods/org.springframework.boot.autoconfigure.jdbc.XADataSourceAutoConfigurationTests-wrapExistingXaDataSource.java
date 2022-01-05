@Test public void wrapExistingXaDataSource() throws Exception {
  ApplicationContext context=createContext(WrapExisting.class);
  context.getBean(DataSource.class);
  XADataSource source=context.getBean(XADataSource.class);
  MockXADataSourceWrapper wrapper=context.getBean(MockXADataSourceWrapper.class);
  assertThat(wrapper.getXaDataSource(),equalTo(source));
}
