package restworld.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
	
	private final LocalValidatorFactoryBean localValidatorFactory = new LocalValidatorFactoryBean();

	public WebMvcConfiguration(SpringConstraintValidatorFactoryExtension beanFactory) {
		localValidatorFactory.setConstraintValidatorFactory(beanFactory);
	}
	
	@Override
	public Validator getValidator() {
		return localValidatorFactory;
	}

}
