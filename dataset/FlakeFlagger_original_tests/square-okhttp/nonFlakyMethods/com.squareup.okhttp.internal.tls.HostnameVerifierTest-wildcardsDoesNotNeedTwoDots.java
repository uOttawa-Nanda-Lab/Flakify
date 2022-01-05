/** 
 * Earlier implementations of Android's hostname verifier required that wildcard names wouldn't match "*.com" or similar. This was a nonstandard check that we've since dropped. It is the CA's responsibility to not hand out certificates that match so broadly.
 */
@Test public void wildcardsDoesNotNeedTwoDots() throws Exception {
  SSLSession session=session("" + "-----BEGIN CERTIFICATE-----\n" + "MIIBjDCCATagAwIBAgIJAOVulXCSu6HuMA0GCSqGSIb3DQEBBQUAMBAxDjAMBgNV\n"+ "BAMUBSouY29tMCAXDTEwMTIyMDE2NDkzOFoYDzIxMTAxMTI2MTY0OTM4WjAQMQ4w\n"+ "DAYDVQQDFAUqLmNvbTBcMA0GCSqGSIb3DQEBAQUAA0sAMEgCQQDJd8xqni+h7Iaz\n"+ "ypItivs9kPuiJUqVz+SuJ1C05SFc3PmlRCvwSIfhyD67fHcbMdl+A/LrIjhhKZJe\n"+ "1joO0+pFAgMBAAGjcTBvMB0GA1UdDgQWBBS4Iuzf5w8JdCp+EtBfdFNudf6+YzBA\n"+ "BgNVHSMEOTA3gBS4Iuzf5w8JdCp+EtBfdFNudf6+Y6EUpBIwEDEOMAwGA1UEAxQF\n"+ "Ki5jb22CCQDlbpVwkruh7jAMBgNVHRMEBTADAQH/MA0GCSqGSIb3DQEBBQUAA0EA\n"+ "U6LFxmZr31lFyis2/T68PpjAppc0DpNQuA2m/Y7oTHBDi55Fw6HVHCw3lucuWZ5d\n"+ "qUYo4ES548JdpQtcLrW2sA==\n"+ "-----END CERTIFICATE-----");
  assertTrue(verifier.verify("google.com",session));
}
