package readinglist.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import readinglist.reader.ReaderRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	ReaderRepository readerRepository;
	
	
	//csrf跨站请求伪造
	private CsrfTokenRepository csrfTokenRepository(){
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setSessionAttributeName("_csrf");
		return repository;
	}
	
	@Override
	protected  void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new UserDetailsService() {
			
			
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {  //用户登陆页面输入的用户名和密码去数据库中查询
				// TODO Auto-generated method stub
				return readerRepository.findOne(username);
			}
		});
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception { //拦截请求，要求请求/readingList/的请求必须经过用户认证及权限认证，并给出一个用户登陆页面
		http
			.csrf().disable()
			.formLogin()
			.and()
			.authorizeRequests()
				.antMatchers("/readingList/").access("hasRole('READER')");
			
	}
}
