/*
 * Copyright 2022-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.instancio.test.jackson.feed;

import org.instancio.Instancio;
import org.instancio.feed.Feed;
import org.instancio.junit.InstancioExtension;
import org.instancio.settings.FeedFormatType;
import org.instancio.settings.Keys;
import org.instancio.test.support.tags.Feature;
import org.instancio.test.support.tags.FeatureTag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@FeatureTag({Feature.FEED, Feature.SETTINGS})
@ExtendWith(InstancioExtension.class)
class FeedDataFormatViaSettingsJacksonTest {

    @Feed.Source(string = """
            [{ "value": "foo" }]
            """)
    private interface SampleFeed extends Feed {}

    @Test
    void shouldUseDataFormatTypeSpecifiedViaSettings() {
        final Feed result = Instancio.ofFeed(SampleFeed.class)
                .withSetting(Keys.FEED_FORMAT_TYPE, FeedFormatType.JSON)
                .create();

        assertThat(result.stringSpec("value").get()).isEqualTo("foo");
    }
}
