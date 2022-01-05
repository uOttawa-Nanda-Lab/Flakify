@Test public void shouldUseConfiguredNamingStrategy() throws Exception {
  setWroWithValidResources();
  final File extraConfigFile=new File(FileUtils.getTempDirectory(),"groupMapping-" + new Date().getTime());
  final Properties props=new Properties();
  props.setProperty(ConfigurableNamingStrategy.KEY,FolderHashEncoderNamingStrategy.ALIAS);
  props.list(new PrintStream(extraConfigFile));
  victim.setWroManagerFactory(ConfigurableWroManagerFactory.class.getName());
  victim.setExtraConfigFile(extraConfigFile);
  victim.setIgnoreMissingResources(true);
  victim.execute();
  FileUtils.deleteQuietly(extraConfigFile);
}
