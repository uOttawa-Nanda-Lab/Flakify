/** 
 * Test filter serialization
 * @throws Exception
 */
public void testSerialization() throws Exception {
  Filter f=new PageFilter(ROW_LIMIT);
  ByteArrayOutputStream stream=new ByteArrayOutputStream();
  DataOutputStream out=new DataOutputStream(stream);
  f.write(out);
  out.close();
  byte[] buffer=stream.toByteArray();
  DataInputStream in=new DataInputStream(new ByteArrayInputStream(buffer));
  Filter newFilter=new PageFilter();
  newFilter.readFields(in);
  pageSizeTests(newFilter);
}
