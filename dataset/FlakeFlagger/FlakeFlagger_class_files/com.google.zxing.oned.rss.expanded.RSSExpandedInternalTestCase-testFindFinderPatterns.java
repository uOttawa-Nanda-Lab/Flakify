/*
 * Copyright (C) 2010 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * These authors would like to acknowledge the Spanish Ministry of Industry,
 * Tourism and Trade, for the support in the project TSI020301-2008-2
 * "PIRAmIDE: Personalizable Interactions with Resources on AmI-enabled
 * Mobile Dynamic Environments", led by Treelogic
 * ( http://www.treelogic.com/ ):
 *
 *   http://www.piramidepse.com/
 */

package com.google.zxing.oned.rss.expanded;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.BufferedImageLuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.oned.rss.DataCharacter;
import com.google.zxing.oned.rss.FinderPattern;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Pablo Orduña, University of Deusto (pablo.orduna@deusto.es)
 * @author Eduardo Castillejo, University of Deusto (eduardo.castillejo@deusto.es)
 */
public final class RSSExpandedInternalTestCase extends Assert {

  @Test public void testFindFinderPatterns() throws Exception{String path="test/data/blackbox/rssexpanded-1/2.png";File file=new File(path);if (!file.exists()){file=new File("core",path);}BufferedImage image=ImageIO.read(file);BinaryBitmap binaryMap=new BinaryBitmap(new GlobalHistogramBinarizer(new BufferedImageLuminanceSource(image)));int rowNumber=binaryMap.getHeight() / 2;BitArray row=binaryMap.getBlackRow(rowNumber,null);List<ExpandedPair> previousPairs=new ArrayList<ExpandedPair>();RSSExpandedReader rssExpandedReader=new RSSExpandedReader();ExpandedPair pair1=rssExpandedReader.retrieveNextPair(row,previousPairs,rowNumber);previousPairs.add(pair1);FinderPattern finderPattern=pair1.getFinderPattern();assertNotNull(finderPattern);assertEquals(0,finderPattern.getValue());ExpandedPair pair2=rssExpandedReader.retrieveNextPair(row,previousPairs,rowNumber);previousPairs.add(pair2);finderPattern=pair2.getFinderPattern();assertNotNull(finderPattern);assertEquals(1,finderPattern.getValue());ExpandedPair pair3=rssExpandedReader.retrieveNextPair(row,previousPairs,rowNumber);previousPairs.add(pair3);finderPattern=pair3.getFinderPattern();assertNotNull(finderPattern);assertEquals(1,finderPattern.getValue());try {rssExpandedReader.retrieveNextPair(row,previousPairs,rowNumber);fail(NotFoundException.class.getName() + " expected");} catch (NotFoundException nfe){}}
}
