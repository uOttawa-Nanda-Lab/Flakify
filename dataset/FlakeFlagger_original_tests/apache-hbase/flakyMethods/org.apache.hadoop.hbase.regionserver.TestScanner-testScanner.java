/** 
 * The test!
 * @throws IOException
 */
public void testScanner() throws IOException {
  try {
    r=createNewHRegion(TESTTABLEDESC,null,null);
    region=new HRegionIncommon(r);
    Put put=new Put(ROW_KEY,System.currentTimeMillis(),null);
    ByteArrayOutputStream byteStream=new ByteArrayOutputStream();
    DataOutputStream s=new DataOutputStream(byteStream);
    REGION_INFO.write(s);
    put.add(HConstants.CATALOG_FAMILY,HConstants.REGIONINFO_QUALIFIER,byteStream.toByteArray());
    region.put(put);
    scan(false,null);
    getRegionInfo();
    r.close();
    r=openClosedRegion(r);
    region=new HRegionIncommon(r);
    scan(false,null);
    getRegionInfo();
    HServerAddress address=new HServerAddress("foo.bar.com:1234");
    put=new Put(ROW_KEY,System.currentTimeMillis(),null);
    put.add(HConstants.CATALOG_FAMILY,HConstants.SERVER_QUALIFIER,Bytes.toBytes(address.toString()));
    region.put(put);
    scan(true,address.toString());
    getRegionInfo();
    region.flushcache();
    scan(true,address.toString());
    getRegionInfo();
    r.close();
    r=openClosedRegion(r);
    region=new HRegionIncommon(r);
    scan(true,address.toString());
    getRegionInfo();
    address=new HServerAddress("bar.foo.com:4321");
    put=new Put(ROW_KEY,System.currentTimeMillis(),null);
    put.add(HConstants.CATALOG_FAMILY,HConstants.SERVER_QUALIFIER,Bytes.toBytes(address.toString()));
    region.put(put);
    scan(true,address.toString());
    getRegionInfo();
    region.flushcache();
    scan(true,address.toString());
    getRegionInfo();
    r.close();
    r=openClosedRegion(r);
    region=new HRegionIncommon(r);
    scan(true,address.toString());
    getRegionInfo();
    r.close();
    r.getLog().closeAndDelete();
  }
  finally {
    shutdownDfs(cluster);
  }
}
