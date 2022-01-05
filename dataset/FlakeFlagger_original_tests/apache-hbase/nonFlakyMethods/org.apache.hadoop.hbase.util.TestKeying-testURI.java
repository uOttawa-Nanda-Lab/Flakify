/** 
 * Test url transformations
 * @throws Exception
 */
public void testURI() throws Exception {
  checkTransform("http://abc:bcd@www.example.com/index.html" + "?query=something#middle");
  checkTransform("file:///usr/bin/java");
  checkTransform("dns:www.powerset.com");
  checkTransform("dns://dns.powerset.com/www.powerset.com");
  checkTransform("http://one.two.three/index.html");
  checkTransform("https://one.two.three:9443/index.html");
  checkTransform("ftp://one.two.three/index.html");
  checkTransform("filename");
}
