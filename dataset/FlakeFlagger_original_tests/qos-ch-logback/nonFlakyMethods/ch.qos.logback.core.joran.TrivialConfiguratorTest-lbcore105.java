@Test public void lbcore105() throws IOException, JoranException {
  String jarEntry="buzz.xml";
  File jarFile=makeRandomJarFile();
  fillInJarFile(jarFile,jarEntry);
  URL url=asURL(jarFile,jarEntry);
  TrivialConfigurator tc=new TrivialConfigurator(rulesMap);
  tc.setContext(context);
  tc.doConfigure(url);
  assertTrue(jarFile.delete());
  assertFalse(jarFile.exists());
}
