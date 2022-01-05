@Test public void test_windowAbsolutePathBeginWithSlashIsValid() throws Exception {
  final String osProperty=System.getProperties().getProperty("os.name");
  if (!osProperty.toLowerCase().contains("windows"))   return;
  System.out.println("Test Windows valid path string.");
  File f0=new File("C:/Windows");
  File f1=new File("/C:/Windows");
  File f2=new File("C:\\Windows");
  File f3=new File("/C:\\Windows");
  File f4=new File("\\C:\\Windows");
  assertEquals(f0,f1);
  assertEquals(f0,f2);
  assertEquals(f0,f3);
  assertEquals(f0,f4);
}
