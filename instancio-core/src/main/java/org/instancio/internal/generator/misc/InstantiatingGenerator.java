/*
 * Copyright 2022 the original author or authors.
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
package org.instancio.internal.generator.misc;

import org.instancio.Random;
import org.instancio.generator.Generator;
import org.instancio.internal.reflection.instantiation.Instantiator;

public class InstantiatingGenerator implements Generator<Object> {

    private final Instantiator instantiator;
    private final Class<?> targetType;

    public InstantiatingGenerator(final Instantiator instantiator, final Class<?> targetType) {
        this.targetType = targetType;
        this.instantiator = instantiator;
    }

    @Override
    public Object generate(final Random random) {
        return instantiator.instantiate(targetType);
    }
}
