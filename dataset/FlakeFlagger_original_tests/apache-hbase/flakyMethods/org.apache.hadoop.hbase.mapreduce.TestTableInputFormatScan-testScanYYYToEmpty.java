/** 
 * Tests a MR scan using specific start and stop rows.
 * @throws IOException
 * @throws ClassNotFoundException
 * @throws InterruptedException
 */
@Test public void testScanYYYToEmpty() throws IOException, InterruptedException, ClassNotFoundException {
  testScan("yyy",null,"zzz");
}
