@Test public void testDatasource() throws Exception {
  InitialContext context=new InitialContext();
  DataSource ds=(DataSource)context.lookup(JNDI_NAME);
  Connection conn=ds.getConnection();
  ResultSet rs=conn.prepareStatement("select 1").executeQuery();
  Assert.assertTrue(rs.next());
  conn.close();
}
