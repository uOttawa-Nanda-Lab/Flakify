@Test public void testJBossXsds() throws Exception {
  for (  File xsdFile : jbossSchemaFiles(false))   validateXsd(xsdFile);
}
