package readinglist.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("amazon") //注入带amazon前缀的属性，稍后用setter注入进来
public class AmazonProperties {

	private String associateId;
	
	public void setAssociateId(String associateId){
		this.associateId = associateId;
	}
	
	public String getAssociateId(){
		return associateId;
	}
}

