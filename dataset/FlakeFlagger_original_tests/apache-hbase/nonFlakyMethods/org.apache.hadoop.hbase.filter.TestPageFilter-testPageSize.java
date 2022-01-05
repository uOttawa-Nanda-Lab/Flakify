/** 
 * test page size filter
 * @throws Exception
 */
public void testPageSize() throws Exception {
  Filter f=new PageFilter(ROW_LIMIT);
  pageSizeTests(f);
}
