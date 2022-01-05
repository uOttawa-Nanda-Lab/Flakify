@Test public void testJdrZipName() throws Exception {
  JdrEnvironment env=new JdrEnvironment();
  env.setJbossHome("/foo/bar/baz");
  env.setHostControllerName("host");
  env.setOutputDirectory("target");
  String name;
  JdrZipFile zf=new JdrZipFile(env);
  try {
    name=zf.name();
    zf.close();
  }
  finally {
    safeClose(zf);
    File f=new File(zf.name());
    f.delete();
  }
  assertTrue(name.endsWith(".zip"));
  assertTrue(name.contains("host"));
  assertTrue(name.startsWith("target"));
}
