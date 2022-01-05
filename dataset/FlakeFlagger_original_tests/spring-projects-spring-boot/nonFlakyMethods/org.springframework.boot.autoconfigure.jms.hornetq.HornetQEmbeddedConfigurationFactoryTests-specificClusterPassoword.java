@Test public void specificClusterPassoword() throws Exception {
  HornetQProperties properties=new HornetQProperties();
  properties.getEmbedded().setClusterPassword("password");
  Configuration configuration=new HornetQEmbeddedConfigurationFactory(properties).createConfiguration();
  assertThat(configuration.getClusterPassword(),equalTo("password"));
}
