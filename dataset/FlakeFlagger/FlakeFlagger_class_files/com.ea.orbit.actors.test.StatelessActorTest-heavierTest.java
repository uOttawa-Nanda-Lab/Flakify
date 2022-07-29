/*
Copyright (C) 2015 Electronic Arts Inc.  All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:

1.  Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
2.  Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
3.  Neither the name of Electronic Arts, Inc. ("EA") nor the names of
    its contributors may be used to endorse or promote products derived
    from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY ELECTRONIC ARTS AND ITS CONTRIBUTORS "AS IS" AND ANY
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL ELECTRONIC ARTS OR ITS CONTRIBUTORS BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package com.ea.orbit.actors.test;

import com.ea.orbit.actors.OrbitStage;
import com.ea.orbit.actors.test.actors.IStatelessThing;
import com.ea.orbit.exception.UncheckedException;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StatelessActorTest extends ActorBaseTest
{

    /**
	 * Sends a bit more messages trying to uncover concurrency issues.
	 */@Test public void heavierTest() throws ExecutionException,InterruptedException{OrbitStage stage1=createStage();OrbitStage stage2=createStage();IStatelessThing actor1=stage1.getReference(IStatelessThing.class,"1000");IStatelessThing actor2=stage2.getReference(IStatelessThing.class,"1000");final Set<UUID> set=new HashSet<>();set.clear();List<Future<UUID>> futures=new ArrayList<>();for (int i=0;i < 50;i++){futures.add(actor1.getUniqueActivationId());futures.add(actor2.getUniqueActivationId());}futures.forEach(f->{try {set.add(f.get(10,TimeUnit.SECONDS));} catch (Exception e){throw new UncheckedException(e);}});assertTrue(set.size() > 1);assertTrue(set.size() <= 100);}

}
