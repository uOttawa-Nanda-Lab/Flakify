@Test public void testWithDatasourceHasNextResult() throws SQLException {
  Map<String,DataSource> map=new HashMap<String,DataSource>();
  DataSource dataSource=mock(DataSource.class);
  Connection connection=mock(Connection.class,Answers.RETURNS_DEEP_STUBS);
  given(dataSource.getConnection()).willReturn(connection);
  given(connection.getMetaData().getTypeInfo().next()).willReturn(true);
  map.put("mockDatabase",dataSource);
  given(applicationContext.getBeansOfType(eq(DataSource.class),anyBoolean(),anyBoolean())).willReturn(map);
  Status status=dataSourceStatusChecker.check();
  assertThat(status.getLevel(),is(Status.Level.OK));
}
