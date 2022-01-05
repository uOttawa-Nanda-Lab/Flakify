/** 
 * http://tools.ietf.org/html/draft-ietf-httpbis-header-compression-05#appendix-E.2
 */
@Test public void readRequestExamplesWithoutHuffman() throws IOException {
  OkBuffer out=firstRequestWithoutHuffman();
  bytesIn.write(out,out.size());
  hpackReader.readHeaders();
  hpackReader.emitReferenceSet();
  checkReadFirstRequestWithoutHuffman();
  out=secondRequestWithoutHuffman();
  bytesIn.write(out,out.size());
  hpackReader.readHeaders();
  hpackReader.emitReferenceSet();
  checkReadSecondRequestWithoutHuffman();
  out=thirdRequestWithoutHuffman();
  bytesIn.write(out,out.size());
  hpackReader.readHeaders();
  hpackReader.emitReferenceSet();
  checkReadThirdRequestWithoutHuffman();
}
