@Test public void longTermFailure(){
  long offset=RecoveryCoordinator.BACKOFF_COEFFICIENT_MIN;
  int tooSoonCount=0;
  for (int i=0; i < 16; i++) {
    rc.setCurrentTime(now + offset);
    if (rc.isTooSoon()) {
      System.out.println("isTooSoon successful at " + (offset));
      tooSoonCount++;
    }
 else {
    }
    offset*=2;
  }
  assertEquals(8,tooSoonCount);
}
