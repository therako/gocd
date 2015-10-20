/*************************GO-LICENSE-START*********************************
 * Copyright 2014 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *************************GO-LICENSE-END***********************************/
package com.thoughtworks.go.config.crud;

import com.thoughtworks.go.config.ConfigCache;
import com.thoughtworks.go.config.MagicalGoConfigXmlLoader;
import com.thoughtworks.go.config.MagicalGoConfigXmlWriter;
import com.thoughtworks.go.metrics.service.MetricsProbeService;
import com.thoughtworks.go.util.ConfigElementImplementationRegistryMother;
import com.thoughtworks.go.util.SystemEnvironment;
import org.junit.Before;

import java.io.ByteArrayOutputStream;

import static org.mockito.Mockito.mock;

public abstract class BaseConfigXmlWriterTest {
    ByteArrayOutputStream output;
    MagicalGoConfigXmlWriter xmlWriter;
    SystemEnvironment systemEnvironment;
    MagicalGoConfigXmlLoader xmlLoader;

    @Before
    public void setup() {
        MetricsProbeService metricsProbeService = mock(MetricsProbeService.class);
        output = new ByteArrayOutputStream();
        ConfigCache configCache = new ConfigCache();
        xmlWriter = new MagicalGoConfigXmlWriter(configCache, ConfigElementImplementationRegistryMother.withNoPlugins(), metricsProbeService);
        xmlLoader = new MagicalGoConfigXmlLoader(configCache, ConfigElementImplementationRegistryMother.withNoPlugins(), metricsProbeService);
    }
}
