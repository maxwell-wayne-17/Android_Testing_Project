package com.example.testing_project.suite;

import com.example.testing_project.ExampleUnitTest;
import com.example.testing_project.ExampleMockTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all unit tests from both ExampleUnitTest and ExampleMockTest
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ExampleUnitTest.class,
    ExampleMockTest.class})
public class UnitTestSuite {}
