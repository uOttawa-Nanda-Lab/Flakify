@Test public void testTooManyErrors(){
  int[] received=PDF417_TEST_WITH_EC.clone();
  Random random=getRandom();
  corrupt(received,MAX_ERRORS + 3,random);
  try {
    checkDecode(received);
    fail("Should not have decoded");
  }
 catch (  ChecksumException ce) {
  }
}
