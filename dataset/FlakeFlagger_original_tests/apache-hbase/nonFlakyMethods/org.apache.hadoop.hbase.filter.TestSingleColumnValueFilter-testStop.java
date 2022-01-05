/** 
 * Tests identification of the stop row
 * @throws Exception
 */
public void testStop() throws Exception {
  basicFilterTests((SingleColumnValueFilter)basicFilter);
  substrFilterTests(substrFilter);
  regexFilterTests(regexFilter);
}
