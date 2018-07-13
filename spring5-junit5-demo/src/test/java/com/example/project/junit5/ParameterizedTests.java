package com.example.project.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.EnumSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;

import com.example.project.Calculator;

import org.junit.jupiter.params.provider.MethodSource;
// 动态测试参数
public class ParameterizedTests {
	@ParameterizedTest(name = "{0} + {1} = {2}")
	@CsvSource({ "0,    1,   1", "1,    2,   3", "49,  51, 100", "1,  100, 101" })
	void add(int first, int second, int expectedResult) {
		Calculator calculator = new Calculator();
		assertEquals(expectedResult, calculator.add(first, second),
				() -> first + " + " + second + " should equal " + expectedResult);
	}

	@ParameterizedTest
	@EnumSource(TimeUnit.class)
	void testWithEnumSource(TimeUnit timeUnit) {
		assertNotNull(timeUnit);
	}

	@ParameterizedTest
	@EnumSource(value = TimeUnit.class, names = { "DAYS", "HOURS" })
	void testWithEnumSourceInclude(TimeUnit timeUnit) {
		assertTrue(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
	}

	@ParameterizedTest
	@EnumSource(value = TimeUnit.class, mode = Mode.EXCLUDE, names = { "DAYS", "HOURS" })
	void testWithEnumSourceExclude(TimeUnit timeUnit) {
		assertFalse(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
		assertTrue(timeUnit.name().length() > 5);
	}

	@ParameterizedTest
	@EnumSource(value = TimeUnit.class, mode = Mode.MATCH_ALL, names = "^(M|N).+SECONDS$")
	void testWithEnumSourceRegex(TimeUnit timeUnit) {
		String name = timeUnit.name();
		assertTrue(name.startsWith("M") || name.startsWith("N"));
		assertTrue(name.endsWith("SECONDS"));
	}

	@ParameterizedTest
	// @MethodSource allows you to refer to one or more factory methods of the test
	// class or external classes. Such factory methods must return a Stream,
	// Iterable, Iterator, or array of arguments.
	// In addition, such factory methods must not accept any arguments. Factory
	// methods within the test class must be static unless the test class is
	// annotated with @TestInstance(Lifecycle.PER_CLASS); whereas, factory methods
	// in external classes must always be static.
	@MethodSource("stringProvider")
	void testWithSimpleMethodSource(String argument) {
		assertNotNull(argument);
	}

	static Stream<String> stringProvider() {
		return Stream.of("foo", "bar");
	}

	/**
	 * If you do not explicitly provide a factory method name via @MethodSource,
	 * JUnit Jupiter will search for a factory method that has the same name as the
	 * current @ParameterizedTest method by convention. This is demonstrated in the
	 * following example.
	 * 
	 * @param argument
	 */
	@ParameterizedTest
	@MethodSource
	void testWithSimpleMethodSourceHavingNoValue(String argument) {
		assertNotNull(argument);
	}

	static Stream<String> testWithSimpleMethodSourceHavingNoValue() {
		return Stream.of("foo", "bar");
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/two-column.csv", numLinesToSkip = 1)
	void testWithCsvFileSource(String first, int second) {
	    assertNotNull(first);
	    assertNotEquals(0, second);
	}
	
	//	@ArgumentsSource can be used to specify a custom, reusable ArgumentsProvider.
	@ParameterizedTest
	@ArgumentsSource(MyArgumentsProvider.class)
	void testWithArgumentsSource(String argument) {
	    assertNotNull(argument);
	}

	public class MyArgumentsProvider implements ArgumentsProvider {

	    @Override
	    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
	        return Stream.of("foo", "bar").map(Arguments::of);
	    }
	}
}
