/** 
 * The test 
 */
public void testRootPath(){
  try {
    FSUtils.validateRootPath(new Path("file:///tmp/hbase/hbase"));
  }
 catch (  IOException e) {
    LOG.fatal("Unexpected exception checking valid path:",e);
    fail();
  }
  try {
    FSUtils.validateRootPath(new Path("hdfs://a:9000/hbase"));
  }
 catch (  IOException e) {
    LOG.fatal("Unexpected exception checking valid path:",e);
    fail();
  }
  try {
    FSUtils.validateRootPath(new Path("/hbase"));
    fail();
  }
 catch (  IOException e) {
    LOG.info("Got expected exception when checking invalid path:",e);
  }
}
