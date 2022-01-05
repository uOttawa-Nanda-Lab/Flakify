/** 
 * Test ServerInfo serialization
 * @throws Exception
 */
public void testServerInfo() throws Exception {
  HServerInfo hsi=new HServerInfo(new HServerAddress("0.0.0.0:123"),-1,1245,"default name");
  byte[] b=Writables.getBytes(hsi);
  HServerInfo deserializedHsi=(HServerInfo)Writables.getWritable(b,new HServerInfo());
  assertTrue(hsi.equals(deserializedHsi));
}
