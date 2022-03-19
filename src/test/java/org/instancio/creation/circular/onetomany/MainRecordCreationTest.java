package org.instancio.creation.circular.onetomany;

import org.instancio.pojo.circular.onetomany.MainRecord;
import org.instancio.testsupport.tags.CyclicTag;
import org.instancio.testsupport.templates.AutoVerificationDisabled;
import org.instancio.testsupport.templates.CreationTestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@CyclicTag
public class MainRecordCreationTest extends CreationTestTemplate<MainRecord> {

    @Override
    @AutoVerificationDisabled
    protected void verify(MainRecord result) {
        assertThat(result.getDetailRecords()).isNotEmpty()
                .allSatisfy(detail -> {
                    assertThat(detail.getMainRecord()).isNotNull();
                    assertThat(detail.getMainRecord().getDetailRecords()).isNotEmpty()
                            .allSatisfy(innerDetail -> assertThat(innerDetail.getMainRecord())
                                    .as("Reached MainRecord again - null value to break the cycle")
                                    .isNull());
                });
    }

}
