/** 
 * the test
 * @throws UnsupportedEncodingException
 */
public void testBase64() throws UnsupportedEncodingException {
  TreeMap<String,String> sorted=new TreeMap<String,String>();
  for (int i=0; i < uris.length; i++) {
    byte[] bytes=uris[i].getBytes("UTF-8");
    sorted.put(Base64.encodeBytes(bytes,Base64.ORDERED),uris[i]);
  }
  System.out.println();
  int i=0;
  for (  Map.Entry<String,String> e : sorted.entrySet()) {
    assertTrue(uris[i++].compareTo(e.getValue()) == 0);
  }
}
