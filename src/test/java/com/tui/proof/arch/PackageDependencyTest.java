package com.tui.proof.arch;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.tui.proof")
public class PackageDependencyTest {

    private static final JavaClasses classes =
            new ClassFileImporter().importPackages("com.tui.proof");

    @ArchTest
    public static final ArchRule applicationShouldNotDependOnInfrastructure =
    noClasses()
            .that()
            .resideInAPackage("..application..")
            .should()
            .dependOnClassesThat()
            .resideInAPackage("..infrastructure..");

    @Test
    public void runArchUnitTests() {applicationShouldNotDependOnInfrastructure.check(classes);}
}
