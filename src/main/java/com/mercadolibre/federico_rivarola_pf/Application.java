package com.mercadolibre.federico_rivarola_pf;

import com.mercadolibre.federico_rivarola_pf.config.SpringConfig;
import com.mercadolibre.federico_rivarola_pf.util.ScopeUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		ScopeUtils.calculateScopeSuffix();
		new SpringApplicationBuilder(SpringConfig.class).registerShutdownHook(true)
				.run(args);
	}
}
