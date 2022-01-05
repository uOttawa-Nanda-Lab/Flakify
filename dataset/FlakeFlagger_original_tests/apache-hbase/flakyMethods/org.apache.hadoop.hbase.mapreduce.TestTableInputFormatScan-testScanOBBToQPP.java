/** 
 * Tests a MR scan using specific start and stop rows.
 * @throws IOException
 * @throws ClassNotFoundException
 * @throws InterruptedException
 */
@Test public void testScanOBBToQPP() throws IOException, InterruptedException, ClassNotFoundException {
  testScan("obb","qpp","qpo");
}
