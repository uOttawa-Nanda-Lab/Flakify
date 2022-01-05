public void testMyBatisConnectionPoolProperlyConfigured(){
  ProcessEngineConfigurationImpl config=(ProcessEngineConfigurationImpl)ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("org/activiti/engine/test/db/connection-pool.activiti.cfg.xml");
  config.buildProcessEngine();
  int maxActive=25;
  int maxIdle=10;
  int maxCheckoutTime=30000;
  int maxWaitTime=25000;
  assertEquals(maxActive,config.getJdbcMaxActiveConnections());
  assertEquals(maxIdle,config.getJdbcMaxIdleConnections());
  assertEquals(maxCheckoutTime,config.getJdbcMaxCheckoutTime());
  assertEquals(maxWaitTime,config.getJdbcMaxWaitTime());
  DataSource datasource=config.getDbSqlSessionFactory().getSqlSessionFactory().getConfiguration().getEnvironment().getDataSource();
  assertTrue(datasource instanceof PooledDataSource);
  PooledDataSource pooledDataSource=(PooledDataSource)datasource;
  assertEquals(maxActive,pooledDataSource.getPoolMaximumActiveConnections());
  assertEquals(maxIdle,pooledDataSource.getPoolMaximumIdleConnections());
  assertEquals(maxCheckoutTime,pooledDataSource.getPoolMaximumCheckoutTime());
  assertEquals(maxWaitTime,pooledDataSource.getPoolTimeToWait());
}
