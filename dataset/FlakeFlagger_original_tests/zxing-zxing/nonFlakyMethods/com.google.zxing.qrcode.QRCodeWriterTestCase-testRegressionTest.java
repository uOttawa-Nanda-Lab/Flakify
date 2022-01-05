@Test public void testRegressionTest() throws Exception {
  compareToGoldenFile("http://www.google.com/",ErrorCorrectionLevel.M,99,"renderer-test-01.png");
}
