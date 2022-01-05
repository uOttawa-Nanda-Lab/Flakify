@Test public void getPoolSizeTwoConnections(){
  final JdbcTemplate jdbcTemplate=new JdbcTemplate(getDataSourceMetadata().getDataSource());
  jdbcTemplate.execute(new ConnectionCallback<Void>(){
    @Override public Void doInConnection(    Connection connection) throws SQLException, DataAccessException {
      jdbcTemplate.execute(new ConnectionCallback<Void>(){
        @Override public Void doInConnection(        Connection connection) throws SQLException, DataAccessException {
          assertEquals(Integer.valueOf(2),getDataSourceMetadata().getActive());
          assertEquals(Float.valueOf(1F),getDataSourceMetadata().getUsage());
          return null;
        }
      }
);
      return null;
    }
  }
);
}
