@Test public void multipleDataSources(){
  load(MultipleDataSourcesConfig.class);
  PublicMetrics bean=this.context.getBean(PublicMetrics.class);
  Collection<Metric<?>> metrics=bean.metrics();
  assertMetrics(metrics,"datasource.tomcat.active","datasource.tomcat.usage","datasource.commonsDbcp.active","datasource.commonsDbcp.usage");
  JdbcTemplate jdbcTemplate=new JdbcTemplate(this.context.getBean("hikariDS",DataSource.class));
  jdbcTemplate.execute(new ConnectionCallback<Void>(){
    @Override public Void doInConnection(    Connection connection) throws SQLException, DataAccessException {
      return null;
    }
  }
);
  Collection<Metric<?>> anotherMetrics=bean.metrics();
  assertMetrics(anotherMetrics,"datasource.tomcat.active","datasource.tomcat.usage","datasource.hikariDS.active","datasource.hikariDS.usage","datasource.commonsDbcp.active","datasource.commonsDbcp.usage");
}
