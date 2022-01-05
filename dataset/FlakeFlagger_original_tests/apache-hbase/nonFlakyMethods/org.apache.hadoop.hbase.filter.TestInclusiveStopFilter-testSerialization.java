/** 
 * Tests serialization
 * @throws Exception
 */
public void testSerialization() throws Exception {
  ByteArrayOutputStream stream=new ByteArrayOutputStream();
  DataOutputStream out=new DataOutputStream(stream);
  mainFilter.write(out);
  out.close();
  byte[] buffer=stream.toByteArray();
  DataInputStream in=new DataInputStream(new ByteArrayInputStream(buffer));
  Filter newFilter=new InclusiveStopFilter();
  newFilter.readFields(in);
  stopRowTests(newFilter);
}
