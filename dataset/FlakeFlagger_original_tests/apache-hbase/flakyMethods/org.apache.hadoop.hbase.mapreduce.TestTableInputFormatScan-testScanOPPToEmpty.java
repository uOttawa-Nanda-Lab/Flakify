/** 
 * Tests a MR scan using specific start and stop rows.
 * @throws IOException
 * @throws ClassNotFoundException
 * @throws InterruptedException
 */
@Test public void testScanOPPToEmpty() throws IOException, InterruptedException, ClassNotFoundException {
  testScan("opp",null,"zzz");
}
