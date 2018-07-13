package com.example.project.junit5;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

public class Conditions {

	@Test
	@EnabledOnOs(OS.MAC)
	void onlyOnMacOs() {
		// ...
	}

	@TestOnMac
	void testOnMac() {
		// ...
	}

	@Test
	@EnabledOnOs({ OS.LINUX, OS.MAC })
	void onLinuxOrMac() {
		// ...
	}

	@Test
	@DisabledOnOs(OS.WINDOWS)
	void notOnWindows() {
		// ...
	}

	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	@Test
	@EnabledOnOs(OS.MAC)
	@interface TestOnMac {
	}

	@Test
	@EnabledOnJre(JRE.JAVA_8)
	void onlyOnJava8() {
		// ...
	}

	@Test
	@EnabledOnJre({ JRE.JAVA_9, JRE.JAVA_10 })
	void onJava9Or10() {
		// ...
	}

	@Test
	@DisabledOnJre(JRE.JAVA_9)
	void notOnJava9() {
		// ...
	}

	@Test
	@EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
	void onlyOn64BitArchitectures() {
		// ...
	}

	@Test
	@DisabledIfSystemProperty(named = "ci-server", matches = "true")
	void notOnCiServer() {
		// ...
	}

	// The value supplied via the matches attribute will be interpreted as a regular
	// expression.
	@Test
	@EnabledIfEnvironmentVariable(named = "ENV", matches = "staging-server")
	void onlyOnStagingServer() {
		// ...
	}

	@Test
	@DisabledIfEnvironmentVariable(named = "ENV", matches = ".*development.*")
	void notOnDeveloperWorkstation() {
		// ...
	}

	// enable or disable a container or test depending on the evaluation of a script
	// configured via
	@Test // Static JavaScript expression.
	@EnabledIf("2 * 3 == 6")
	void willBeExecuted() {
		// ...
	}

	@RepeatedTest(10) // Dynamic JavaScript expression.
	@DisabledIf("Math.random() < 0.314159")
	void mightNotBeExecuted() {
		// ...
	}

	@Test // Regular expression testing bound system property.
	@DisabledIf("/32/.test(systemProperty.get('os.arch'))")
	void disabledOn32BitArchitectures() {
		assertFalse(System.getProperty("os.arch").contains("32"));
	}

	@Test
	@EnabledIf("'CI' == systemEnvironment.get('ENV')")
	void onlyOnCiServer() {
		assertTrue("CI".equals(System.getenv("ENV")));
	}

	@Test // Multi-line script, custom engine name and custom reason.
	@EnabledIf(value = { "load('nashorn:mozilla_compat.js')", "importPackage(java.time)", "",
			"var today = LocalDate.now()", "var tomorrow = today.plusDays(1)",
			"tomorrow.isAfter(today)" }, engine = "nashorn", reason = "Self-fulfilling: {result}")
	void theDayAfterTomorrow() {
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plusDays(1);
		assertTrue(tomorrow.isAfter(today));
	}
}
