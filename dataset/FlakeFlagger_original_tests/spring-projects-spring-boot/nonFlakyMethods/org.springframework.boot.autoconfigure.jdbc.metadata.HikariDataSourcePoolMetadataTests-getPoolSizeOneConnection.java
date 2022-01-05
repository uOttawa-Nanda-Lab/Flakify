@Test public void getPoolSizeOneConnection(){
  JdbcTemplate jdbcTemplate=new JdbcTemplate(getDataSourceMetadata().getDataSource());
  jdbcTemplate.execute(new ConnectionCallback<Void>(){
    @Override public Void doInConnection(    Connection connection) throws SQLException, DataAccessException {
      assertEquals(Integer.valueOf(1),getDataSourceMetadata().getActive());
      assertEquals(Float.valueOf(0.5F),getDataSourceMetadata().getUsage());
      return null;
    }
  }
);
}
