@Test @RunAsClient public void testSystemPropertiesClient() throws Exception {
  for (  String var : EXPECTED_PROPS) {
    String path=System.getProperty(var);
    Assert.assertNotNull("Property " + var + " is not set (outside container).",path);
    File dir=new File(path);
    Assert.assertTrue("Directory " + dir.getAbsolutePath() + " doesn't exist, check Surefire's system property "+ var,dir.exists());
  }
}
