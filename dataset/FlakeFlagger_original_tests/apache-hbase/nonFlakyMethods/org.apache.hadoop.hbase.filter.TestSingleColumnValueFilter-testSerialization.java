/** 
 * Tests serialization
 * @throws Exception
 */
public void testSerialization() throws Exception {
  Filter newFilter=serializationTest(basicFilter);
  basicFilterTests((SingleColumnValueFilter)newFilter);
  newFilter=serializationTest(substrFilter);
  substrFilterTests(newFilter);
  newFilter=serializationTest(regexFilter);
  regexFilterTests(newFilter);
}
