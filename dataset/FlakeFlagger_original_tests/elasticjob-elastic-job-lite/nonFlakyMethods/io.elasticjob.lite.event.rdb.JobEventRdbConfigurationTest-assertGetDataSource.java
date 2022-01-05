@Test public void assertGetDataSource() throws JobEventListenerConfigurationException {
  BasicDataSource dataSource=new BasicDataSource();
  dataSource.setDriverClassName(org.h2.Driver.class.getName());
  dataSource.setUrl("jdbc:h2:mem:job_event_storage");
  dataSource.setUsername("sa");
  dataSource.setPassword("");
  assertThat((BasicDataSource)(new JobEventRdbConfiguration(dataSource).getDataSource()),is(dataSource));
}
