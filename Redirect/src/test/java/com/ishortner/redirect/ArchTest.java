package com.ishortner.redirect;

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
            .importPackages("com.ishortner.redirect");

        noClasses()
            .that()
                .resideInAnyPackage("com.ishortner.redirect.service..")
            .or()
                .resideInAnyPackage("com.ishortner.redirect.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.ishortner.redirect.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
