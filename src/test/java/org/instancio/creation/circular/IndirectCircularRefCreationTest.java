package org.instancio.creation.circular;

import org.instancio.pojo.circular.IndirectCircularRef;
import org.instancio.testsupport.tags.CyclicTag;
import org.instancio.testsupport.templates.AutoVerificationDisabled;
import org.instancio.testsupport.templates.CreationTestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@CyclicTag
public class IndirectCircularRefCreationTest extends CreationTestTemplate<IndirectCircularRef> {

    @Override
    @AutoVerificationDisabled
    protected void verify(IndirectCircularRef result) {
        assertThat(result.getStartA()).isNotNull()
                // A -> B
                .extracting(IndirectCircularRef.A::getB)
                .satisfies(b -> assertThat(b).isNotNull())
                // B -> C
                .extracting(IndirectCircularRef.B::getC)
                .satisfies(c -> assertThat(c).isNotNull())
                // C -> endA
                .extracting(IndirectCircularRef.C::getEndA)
                .satisfies(endA -> assertThat(endA).isNotNull())
                // A -> B
                .extracting(IndirectCircularRef.A::getB)
                .satisfies(b -> assertThat(b).isNotNull())
                // B -> C
                .extracting(IndirectCircularRef.B::getC)
                .satisfies(c -> assertThat(c).isNotNull())
                // C -> endA
                .extracting(IndirectCircularRef.C::getEndA)
                .as("Reached 'endA' again; end cycle with a null value")
                .satisfies(endA -> assertThat(endA).isNull());
    }

}
