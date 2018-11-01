package hoggen.wang.O2O.config.web;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.code.kaptcha.servlet.KaptchaServlet;

@Configuration
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;
	}

	/**
	 * @注释 静态资源配置
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/upload").addResourceLocations("/Users/wangliugen/Desktop/image/upload");
	}

	/**
	 * @注释 定义默认请求
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
		configurer.enable();
	}

	/**
	 * @注释 定义视图解析器
	 */

	@Bean(name = "viewResolver")
	public ViewResolver setViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		// 设置sPring容器！
		viewResolver.setApplicationContext(this.applicationContext);
		// 取消缓存
		viewResolver.setCache(false);
		// 设置解析的前缀
		viewResolver.setPrefix("/WEB-INF/html/");
		// 设置解析的后缀
		viewResolver.setSuffix(".html");
		return viewResolver;
	}

	@Value("${kaptcha.noise.color}")
	private String nColor;
	
	@Value("${kaptcha.textproducer.font.size}")
	private String fsize;
	
	@Value("${kaptcha.image.height}")
	private String height;
	
	@Value("${kaptcha.textproducer.char.string}")
	private String cString;
	
	@Value("${kaptcha.image.width}")
	private String width;
	
	@Value("${kaptcha.textproducer.font.color}")
	private String fcolor;
	
	@Value("${kaptcha.border}")
	private String border;
	
	@Value("${kaptcha.textproducer.char.length}")
	private String clength;

	@Value("${kaptcha.textproducer.font.names}")
	private String fnames;

	/**
	 * @注释 文件上传解析器
	 */
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver setCommonsMultipartResolver() {
		CommonsMultipartResolver viewResolver = new CommonsMultipartResolver();

		viewResolver.setDefaultEncoding("utf-8");

		viewResolver.setMaxUploadSize(20971520);
		viewResolver.setMaxInMemorySize(20971520);
		return viewResolver;
	}

	/**
	 * @注释 验证码配置
	 */
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		ServletRegistrationBean serBean = new ServletRegistrationBean(new KaptchaServlet(), "/Kaptcha");
		serBean.addInitParameter("kaptcha.border", border);
		serBean.addInitParameter("kaptcha.textproducer.font.color", fcolor);
		serBean.addInitParameter("kaptcha.textproducer.font.size", fsize);
		serBean.addInitParameter("kaptcha.textproducer.font.names", fnames);
		serBean.addInitParameter("kaptcha.image.width", width);
		serBean.addInitParameter("kaptcha.textproducer.char.string", cString);

		serBean.addInitParameter("kaptcha.image.height", height);
		serBean.addInitParameter("kaptcha.noise.color", nColor);
		serBean.addInitParameter("kaptcha.textproducer.char.length", clength);
		return serBean;

	}


}
