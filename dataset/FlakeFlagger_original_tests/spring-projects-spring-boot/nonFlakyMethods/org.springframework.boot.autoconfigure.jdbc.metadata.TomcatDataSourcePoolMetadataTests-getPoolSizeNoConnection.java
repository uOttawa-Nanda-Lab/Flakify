@Test public void getPoolSizeNoConnection(){
  JdbcTemplate jdbcTemplate=new JdbcTemplate(getDataSourceMetadata().getDataSource());
  jdbcTemplate.execute(new ConnectionCallback<Void>(){
    @Override public Void doInConnection(    Connection connection) throws SQLException, DataAccessException {
      return null;
    }
  }
);
  assertEquals(Integer.valueOf(0),getDataSourceMetadata().getActive());
  assertEquals(Float.valueOf(0),getDataSourceMetadata().getUsage());
}
