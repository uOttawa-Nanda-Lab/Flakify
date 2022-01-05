/** 
 * Test serialization
 * @throws Exception
 */
public void testSerialization() throws Exception {
  List<Filter> filters=new ArrayList<Filter>();
  filters.add(new PageFilter(MAX_PAGES));
  filters.add(new WhileMatchFilter(new PrefixFilter(Bytes.toBytes("yyy"))));
  Filter filterMPALL=new FilterList(FilterList.Operator.MUST_PASS_ALL,filters);
  ByteArrayOutputStream stream=new ByteArrayOutputStream();
  DataOutputStream out=new DataOutputStream(stream);
  filterMPALL.write(out);
  out.close();
  byte[] buffer=stream.toByteArray();
  DataInputStream in=new DataInputStream(new ByteArrayInputStream(buffer));
  FilterList newFilter=new FilterList();
  newFilter.readFields(in);
}
