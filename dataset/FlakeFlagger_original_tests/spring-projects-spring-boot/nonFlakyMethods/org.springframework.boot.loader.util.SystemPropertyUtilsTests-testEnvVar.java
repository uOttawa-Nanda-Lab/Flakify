@Test public void testEnvVar(){
  assertEquals(System.getenv("LANG"),SystemPropertyUtils.getProperty("lang"));
}
