@Test public void persistenceSetup(){
  HornetQProperties properties=new HornetQProperties();
  properties.getEmbedded().setPersistent(true);
  Configuration configuration=new HornetQEmbeddedConfigurationFactory(properties).createConfiguration();
  assertThat(configuration.isPersistenceEnabled(),equalTo(true));
  assertThat(configuration.getJournalType(),equalTo(JournalType.NIO));
}
