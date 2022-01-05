/** 
 * Test RegionInfo serialization
 * @throws Exception
 */
public void testRegionInfo() throws Exception {
  HTableDescriptor htd=new HTableDescriptor(getName());
  String[] families=new String[]{"info","anchor"};
  for (int i=0; i < families.length; i++) {
    htd.addFamily(new HColumnDescriptor(families[i]));
  }
  HRegionInfo hri=new HRegionInfo(htd,HConstants.EMPTY_START_ROW,HConstants.EMPTY_END_ROW);
  byte[] hrib=Writables.getBytes(hri);
  HRegionInfo deserializedHri=(HRegionInfo)Writables.getWritable(hrib,new HRegionInfo());
  assertEquals(hri.getEncodedName(),deserializedHri.getEncodedName());
  assertEquals(hri.getTableDesc().getFamilies().size(),deserializedHri.getTableDesc().getFamilies().size());
}
