package com.ilyadeveloper.quotegenerator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuoteGeneratorApplicationTests {

	@Autowired
	WisdomOldSayingsParser wisdomOldSayingsParser;

	@Test
	public void testOne(){
		wisdomOldSayingsParser.scanPages();
	}
}
