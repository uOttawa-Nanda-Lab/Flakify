@Test public void testPassFileGen() throws Exception {
  File passFile=new File(temp.getRoot().getAbsolutePath() + File.separator + passFileName);
  assertTrue(passFile.exists());
  String pass=FileUtils.readFileToString(passFile);
  assertEquals(pass.length(),passLen);
}
