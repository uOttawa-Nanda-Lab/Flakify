/** 
 * Tests a MR scan using specific start and stop rows.
 * @throws IOException
 * @throws ClassNotFoundException
 * @throws InterruptedException
 */
@Test public void testScanOBBToOPP() throws IOException, InterruptedException, ClassNotFoundException {
  testScan("obb","opp","opo");
}
