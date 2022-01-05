@Test public void testMaxErrors() throws ChecksumException {
  Random random=getRandom();
  for (int testIterations=0; testIterations < 100; testIterations++) {
    int[] received=PDF417_TEST_WITH_EC.clone();
    corrupt(received,MAX_ERRORS,random);
    checkDecode(received);
  }
}
