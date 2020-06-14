package com.hms.monolithic;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.hms.monolithic");

        noClasses()
            .that()
                .resideInAnyPackage("com.hms.monolithic.service..")
            .or()
                .resideInAnyPackage("com.hms.monolithic.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.hms.monolithic.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
