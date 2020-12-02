package com.ishortner.stats;

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
            .importPackages("com.ishortner.stats");

        noClasses()
            .that()
                .resideInAnyPackage("com.ishortner.stats.service..")
            .or()
                .resideInAnyPackage("com.ishortner.stats.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.ishortner.stats.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
