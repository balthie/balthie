package com.example.project.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Nested tests for HashMap")
public class NestedDemo {
	Map<String, Object> map;

	@Nested
	@DisplayName("when a new")
	class WhenNew {
		@BeforeEach
		void create() {
			map = new HashMap<>();
		}

		@Test
		@DisplayName("is empty")
		void isEmpty() {
			assertTrue(map.isEmpty());
		}

		@Nested
		@DisplayName("after adding a new entry")
		class AfterAdd {
			String key = "key";
			Object value = "value";

			@BeforeEach
			void add() {
				map.put(key, value);
			}

			@Test
			@DisplayName("is not empty")
			void isNotEmpty() {
				assertFalse(map.isEmpty());
			}

			@Test
			@DisplayName("returns value when getting by key")
			void returnValueWhenGettingByKey() {
				assertEquals(value, map.get(key));
			}

			@Nested
			@DisplayName("after removing the entry")
			class AfterRemove {
				@BeforeEach
				void remove() {
					map.remove(key);
				}

				@Test
				@DisplayName("is empty now")
				void isEmpty() {
					assertTrue(map.isEmpty());
				}

				@Test
				@DisplayName("returns null when getting by key")
				void returnNullForKey() {
					assertNull(map.get(key));
				}
			}
		}
	}
}