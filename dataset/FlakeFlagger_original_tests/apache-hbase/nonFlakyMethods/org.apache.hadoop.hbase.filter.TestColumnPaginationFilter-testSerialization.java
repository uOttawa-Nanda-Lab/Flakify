/** 
 * Tests serialization
 * @throws Exception
 */
public void testSerialization() throws Exception {
  Filter newFilter=serializationTest(columnPaginationFilter);
  basicFilterTests((ColumnPaginationFilter)newFilter);
}
