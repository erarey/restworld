package restworld.configuration;

import javax.validation.ConstraintValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

/**
 * Allows validators to search for instances of classes in the IoC container, thereby allowing custom validation annotations to reference an interface without knowing the underlying implementation. Useful when you want seperate projects for your DTO Layer (where you would create the annotation and an associated interface) and Business Layer (where you would create an implementation of the interface)
 * </br></br>Based on the StackOverflow answer linked below
 * @see <a href="http://stackoverflow.com/a/18352754">How to avoid cross dependency between layers because of @Constraint validatedBy?</a>
 */
@Component
public class SpringConstraintValidatorFactoryExtension extends SpringConstraintValidatorFactory {

	private final Logger logger = LoggerFactory.getLogger(SpringConstraintValidatorFactoryExtension.class);

	private final AutowireCapableBeanFactory beanFactory;

	public SpringConstraintValidatorFactoryExtension(AutowireCapableBeanFactory beanFactory) {	
		super(beanFactory);
		this.beanFactory = beanFactory;
	}
	
	@Override
	public <T extends ConstraintValidator<?, ?>> T getInstance(Class<T> key) {
		T bean = null;
		
		try {
			logger.debug("Trying to find a validator bean of class " + key.getSimpleName());
			bean = beanFactory.getBean(key);
		} catch (BeansException exc) {
			logger.debug("Failed to find a bean of class " + key.getSimpleName());
		}

		if (bean == null) {
			try {
				logger.debug("Creating a new validator bean of class " + key.getSimpleName());
				bean = beanFactory.createBean(key);
			} catch (BeansException exc) {
				logger.debug("Failed to create a validator of class " + key.getSimpleName());
			}
		}

		if (bean == null) {
			logger.warn("Failed to get validator of class " + key.getSimpleName());
		}

		return bean;
	}

}