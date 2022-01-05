@Test public void connectionClosed() throws Exception {
  DataSource dataSource=mock(DataSource.class);
  Connection connection=mock(Connection.class);
  given(connection.getMetaData()).willReturn(this.dataSource.getConnection().getMetaData());
  given(dataSource.getConnection()).willReturn(connection);
  this.indicator.setDataSource(dataSource);
  Health health=this.indicator.health();
  assertNotNull(health.getDetails().get("database"));
  verify(connection,times(2)).close();
}
