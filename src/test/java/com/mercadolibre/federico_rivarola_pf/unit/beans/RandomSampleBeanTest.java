package com.mercadolibre.federico_rivarola_pf.unit.beans;

import com.mercadolibre.federico_rivarola_pf.dtos.SampleDTO;
import com.mercadolibre.federico_rivarola_pf.beans.RandomSampleBean;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomSampleBeanTest {

	@Test
	public void randomPositiveTestOK() {
		RandomSampleBean randomSample = new RandomSampleBean();

		SampleDTO sample = randomSample.random();
		
		assertTrue(sample.getRandom() >= 0);
	}
}
