/**
   Copyright 2009, The SemanticVectors AUTHORS.
   All rights reserved.

   Redistribution and use in source and binary forms, with or without
   modification, are permitted provided that the following conditions are
   met:

 * Redistributions of source code must retain the above copyright
   notice, this list of conditions and the following disclaimer.

 * Redistributions in binary form must reproduce the above
   copyright notice, this list of conditions and the following disclaimer
   in the documentation and/or other materials provided with the
   distribution.

 * Neither the name of Google Inc. nor the names of its
   contributors may be used to endorse or promote products derived from
   this software without specific prior written permission.

   THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
   "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
   LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
   A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
   OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
   SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
   LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
   DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
   THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
   (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
   THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 **/

package pitt.search.semanticvectors.integrationtests;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.junit.*;

import pitt.search.semanticvectors.BuildIndex;
import pitt.search.semanticvectors.BuildPositionalIndex;
import pitt.search.semanticvectors.FlagConfig;
import pitt.search.semanticvectors.Search;
import pitt.search.semanticvectors.SearchResult;

import static org.junit.Assert.*;

/**
 * A collection of regression tests, to make sure that new options and
 * features don't break existing usage patterns.
 *
 * Should be run using "ant run-integration-tests", which will run
 * regression using the RunTests class working in the
 * test/testdata/tmp directory. Depends on there being appropriate
 * Lucene indexes in this directory, which are prepared by the
 * RunTests class.
 */
public class RegressionTests {
  @Before
  public void setUp() throws IOException {
    RunTests.prepareTestData();
  }

  private int buildSearchGetRank(String buildCmd, String searchCmd, String targetResult) {
    String[] filesToBuild = new String[] {"termvectors.bin", "docvectors.bin"};
    String[] buildArgs = buildCmd.split("\\s+");
    String[] searchArgs = searchCmd.split("\\s+");
    for (String fn : filesToBuild) {
      File file = new File(fn);
      if (file.isFile()) {
        assertTrue("Failed to delete file: " + fn, file.delete());
      }
      file = null;
      assertFalse("File appears to be still present: " + fn, (new File(fn)).isFile());
    }
    try {
      BuildIndex.main(buildArgs);
    } catch (Exception e) {
      e.printStackTrace();
    }
    for (String fn: filesToBuild) assertTrue((new File(fn)).isFile());

    List<SearchResult> results = Search.runSearch(FlagConfig.getFlagConfig(searchArgs));
    int rank = 1;
    boolean found = false;
    if (results.isEmpty()) {
      throw new RuntimeException("Results were empty!");
    } else {
      for (SearchResult result : results) {
        String term = (String) result.getObjectVector().getObject();
        // This is a platform-specific hack to make tests involving pathnames work on either linux or windows.
        term = term.replace("\\", "/");
        if (term.equals(targetResult)) {
          found = true;
          break;
        }
        ++rank;
      }
    }
    if (!found) {
      fail("Failed to get matching results with search terms: " + searchCmd);
    }
    
    for (String fn: filesToBuild) assertTrue((new File(fn)).delete());
    return rank;
  }

  @Test
  public void testBuildAndSearchBasicRealIndex() {
    assertEquals(2, buildSearchGetRank("-dimension 200 -luceneindexpath positional_index",
        "-queryvectorfile termvectors.bin -searchvectorfile termvectors.bin peter", "simon"));
  }
   
  @Test
  public void testBuildAndSearchBasicRealIndexDocs() {
    assertTrue(3 > buildSearchGetRank("-dimension 200 -vectortype real -luceneindexpath positional_index",
        "-queryvectorfile termvectors.bin -searchvectorfile docvectors.bin peter",
        "src/test/resources/testdata/John/Chapter_21"));
  }

  @Test
  public void testBuildAndSearchBasicComplexIndex() {
    assertEquals(2, buildSearchGetRank(
        "-dimension 200 -vectortype complex -luceneindexpath positional_index", "peter", "simon"));
  }

  @Test
  public void testBuildAndSearchBasicBinaryIndex() {
    assertEquals(2, buildSearchGetRank(
        "-dimension 8192 -seedlength 128 -vectortype binary -luceneindexpath positional_index", "peter", "simon"));
  }

  private int positionalBuildSearchGetRank(
      String buildCmd, String searchCmd, String[] filesToBuild, String targetResult) {
    String[] buildArgs = buildCmd.split("\\s+");
    String[] searchArgs = searchCmd.split("\\s+");

    // This is a platform-specific hack to make tests involving pathnames work on either linux or windows.
    targetResult = targetResult.replace("\\", "/");

    for (String fn : filesToBuild) {
      File file = new File(fn);
      if (file.isFile()) {
        assertTrue("Failed to delete file: " + fn, file.delete());
      }
      file = null;
      assertFalse("File appears to be still present: " + fn, (new File(fn)).isFile());
    }
    BuildPositionalIndex.main(buildArgs);
    for (String fn : filesToBuild) {
      assertTrue("Missing required file: " + fn, new File(fn).isFile());
    }

    List<SearchResult> results = Search.runSearch(FlagConfig.getFlagConfig(searchArgs));
    assertFalse("Search results should not be empty!", results.isEmpty());

    int rank = 1;
    for (SearchResult result : results) {
      String term = (String) result.getObjectVector().getObject();
      // This is a platform-specific hack to make tests involving pathnames work on either linux or windows.
      term = term.replace("\\", "/");
      if (term.equals(targetResult)) break;
      ++rank;
    }

    for (String fn : filesToBuild) assertTrue(new File(fn).delete());
    return rank;
  }

  @Test
  public void testBuildAndSearchRealPositionalIndex() {
    int peterRank = positionalBuildSearchGetRank(
        "-dimension 200 -vectortype real -seedlength 10 -luceneindexpath positional_index",
        "-queryvectorfile termtermvectors.bin simon",
        new String[] {"termtermvectors.bin", "docvectors.bin"},
        "peter");
    assertTrue(peterRank < 20);
  }
    
  @Test
  synchronized public void testBuildAndSearchRealPositionalIndexDocs() {
    int chapter6Rank = positionalBuildSearchGetRank(
        "-dimension 200 -vectortype real -seedlength 10 -luceneindexpath positional_index",
        "-queryvectorfile termtermvectors.bin -searchvectorfile docvectors.bin bread",
        new String[] {"termtermvectors.bin", "docvectors.bin"},
        "src/test/resources/testdata/John/Chapter_6");
    assertEquals(1, chapter6Rank);
  }
  
  @Test
  public void testBuildAndSearchComplexPositionalIndex() {
    int peterRank = positionalBuildSearchGetRank(
        "-dimension 200 -vectortype complex -seedlength 10 -luceneindexpath positional_index",
        "-queryvectorfile termtermvectors.bin simon",
        new String[] {"termtermvectors.bin", "docvectors.bin"},
        "peter");
    assertTrue(peterRank < 5);
  }
  
  @Test
  public void testBuildAndSearchBinaryPositionalIndex() {
    int peterRank = positionalBuildSearchGetRank(
        "-dimension 8192 -vectortype binary -seedlength 4096 -luceneindexpath positional_index",
        "-queryvectorfile termtermvectors.bin simon",
        new String[] {"termtermvectors.bin", "docvectors.bin"},
        "peter");
       assertTrue(peterRank < 10);
  }

  // Convolution for complex directional indexing seems to really need some termweighting to work well. 
  @Test
  public void testBuildAndSearchComplexDirectionalIndex() {
    int peterRank = positionalBuildSearchGetRank(
        "-dimension 200 -vectortype complex -seedlength 10 -positionalmethod directional -termweight idf "
        + "-luceneindexpath positional_index",
        "-queryvectorfile drxntermvectors.bin simon",
        new String[] {"drxntermvectors.bin", "docvectors.bin"},
        "peter");
    assertTrue(peterRank <= 3);
  }
  
  @Test
  public void testBuildAndSearchRealDirectionalIndex() {
    int peterRank = positionalBuildSearchGetRank(
        "-dimension 200 -vectortype real -seedlength 10 -positionalmethod directional -luceneindexpath positional_index",
        "-queryvectorfile drxntermvectors.bin simon",
        new String[] {"drxntermvectors.bin", "docvectors.bin"},
        "peter");
    assertTrue(peterRank <= 10);
  }

  @Test
  public void testBuildAndSearchBinaryDirectionalIndex() {
    int peterRank = positionalBuildSearchGetRank(
        "-dimension 2048 -vectortype binary -seedlength 1024 -positionalmethod directional "
        + "-termweight none -luceneindexpath positional_index",
        "-queryvectorfile drxntermvectors.bin simon",
        new String[] {"drxntermvectors.bin", "docvectors.bin"},
        "peter");
    assertTrue(peterRank <= 4);
  }

  @Test
  public void testBuildAndSearchRealPermutationIndex() {
    int peterRank = positionalBuildSearchGetRank(
        "-dimension 200 -vectortype real -seedlength 10 -positionalmethod permutation -luceneindexpath positional_index",
        "-searchtype permutation -queryvectorfile elementalvectors.bin -searchvectorfile permtermvectors.bin simon ?",
        new String[] {"elementalvectors.bin", "permtermvectors.bin", "docvectors.bin"},
        "peter");
    assertTrue(peterRank <= 2);
  }

  @Test
  public void testBuildAndSearchComplexPermutationIndex() {
    int peterRank = positionalBuildSearchGetRank(
        "-dimension 200 -vectortype complex -seedlength 10 -positionalmethod permutation -luceneindexpath positional_index",
        "-searchtype permutation -queryvectorfile elementalvectors.bin -searchvectorfile permtermvectors.bin simon ?",
        new String[] {"elementalvectors.bin", "permtermvectors.bin", "docvectors.bin"},
        "peter");
    assertTrue(peterRank < 3);
  }

  @Test
  public void testBuildAndSearchBinaryPermutationIndex() {
    int peterRank = positionalBuildSearchGetRank(
        "-dimension 1024 -vectortype binary -seedlength 512 -positionalmethod permutation -luceneindexpath positional_index",
        "-searchtype permutation -queryvectorfile elementalvectors.bin -searchvectorfile permtermvectors.bin simon ?",
        new String[] {"elementalvectors.bin", "permtermvectors.bin", "docvectors.bin"},
        "peter");
    assertTrue(3 >= peterRank);
  }

  /*
   * This last test seems to throw lots of others off in Windows. I wonder if there's
   * some multithreading going on that makes this whole test suite very unsafe - not sure.
   * Test still causes problems with regular permutation search, so I don't think it's a problem
   * with balanced_permutation itself.
   */
  /*
  @Test
  public void testBuildAndSearchRealBalancedPermutationIndex() {
    int peterRank = positionalBuildSearchGetRank(
        "-dimension 200 -vectortype real -seedlength 10 -positionalmethod permutation positional_index",
        "-queryvectorfile elementalvectors.bin -searchvectorfile permtermvectors.bin -searchtype balanced_permutation simon ?",
        new String[] {"elementalvectors.bin", "permtermvectors.bin", "docvectors.bin"},
        "peter");
    assertTrue(peterRank < 5);
  }
  */
}
