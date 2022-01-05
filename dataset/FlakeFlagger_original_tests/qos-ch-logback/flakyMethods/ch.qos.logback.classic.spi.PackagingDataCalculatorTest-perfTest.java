@Test public void perfTest(){
  int len=1000;
  loop(len,false);
  loop(len,true);
  double d0=loop(len,false);
  System.out.println("without packaging info " + d0 + " microseconds");
  double d1=loop(len,true);
  System.out.println("with    packaging info " + d1 + " microseconds");
  int slackFactor=8;
  if (!SystemInfo.getJavaVendor().contains("Sun")) {
    slackFactor=10;
  }
  assertTrue("computing class packaging data (" + d1 + ") should have been less than "+ slackFactor+ " times the time it takes to process an exception "+ (d0 * slackFactor),d0 * slackFactor > d1);
}
