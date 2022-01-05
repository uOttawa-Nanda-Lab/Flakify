@Test public void generatedClusterPassoword() throws Exception {
  HornetQProperties properties=new HornetQProperties();
  Configuration configuration=new HornetQEmbeddedConfigurationFactory(properties).createConfiguration();
  assertThat(configuration.getClusterPassword().length(),equalTo(36));
}
