@Test public void defaultDataDir(){
  HornetQProperties properties=new HornetQProperties();
  properties.getEmbedded().setPersistent(true);
  Configuration configuration=new HornetQEmbeddedConfigurationFactory(properties).createConfiguration();
  assertThat(configuration.getJournalDirectory(),startsWith(System.getProperty("java.io.tmpdir")));
  assertThat(configuration.getJournalDirectory(),endsWith("/journal"));
}
