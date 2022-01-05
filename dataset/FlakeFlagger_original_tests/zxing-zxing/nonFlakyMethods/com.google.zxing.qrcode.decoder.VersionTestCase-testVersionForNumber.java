@Test public void testVersionForNumber(){
  try {
    Version.getVersionForNumber(0);
    fail("Should have thrown an exception");
  }
 catch (  IllegalArgumentException iae) {
  }
  for (int i=1; i <= 40; i++) {
    checkVersion(Version.getVersionForNumber(i),i,4 * i + 17);
  }
}
