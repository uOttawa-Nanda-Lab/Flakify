@Test public void createFromUrl() throws Exception {
  ApplicationContext context=createContext(FromProperties.class,"spring.datasource.url:jdbc:hsqldb:mem:test","spring.datasource.username:un");
  context.getBean(DataSource.class);
  MockXADataSourceWrapper wrapper=context.getBean(MockXADataSourceWrapper.class);
  JDBCXADataSource dataSource=(JDBCXADataSource)wrapper.getXaDataSource();
  assertNotNull(dataSource);
  assertThat(dataSource.getUrl(),equalTo("jdbc:hsqldb:mem:test"));
  assertThat(dataSource.getUser(),equalTo("un"));
}
