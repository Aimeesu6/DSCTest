package com.dsc.tool.spock.extractor.test

import spock.lang.Specification

class GivenExpectAndSetupExpectCleanupSpec extends Specification {
	def "length should be positive"() {
		given: "A string"
		String s = "request"
		expect: "check length"
		s.length() > 0
	}

	def "length should be positive 2"() {
		setup: "A string"
		String s = "request"
		expect: "check length"
		s.length() > 0
		cleanup: "Clean after test"
		s = null
	}
}