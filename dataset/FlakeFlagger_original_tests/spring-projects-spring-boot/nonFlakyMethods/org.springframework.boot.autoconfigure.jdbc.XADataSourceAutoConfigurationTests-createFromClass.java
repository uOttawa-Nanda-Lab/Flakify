@Test public void createFromClass() throws Exception {
  ApplicationContext context=createContext(FromProperties.class,"spring.datasource.xa.data-source-class:org.hsqldb.jdbc.pool.JDBCXADataSource","spring.datasource.xa.properties.database-name:test");
  context.getBean(DataSource.class);
  MockXADataSourceWrapper wrapper=context.getBean(MockXADataSourceWrapper.class);
  JDBCXADataSource dataSource=(JDBCXADataSource)wrapper.getXaDataSource();
  assertNotNull(dataSource);
  assertThat(dataSource.getDatabaseName(),equalTo("test"));
}
