package com.eng.stream.hackathon.bookmark;

import java.sql.Date;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.eng.stream.hackathon.bookmark.models.GroupReference;
import com.eng.stream.hackathon.bookmark.repositories.GroupReferenceRepository;

@SpringBootApplication
@EnableJpaAuditing
public class BookmarkApplication {

	@Autowired
	private GroupReferenceRepository groupReferenceRepo;
	
	/*
	 * @Bean public AuditorAware<String> auditorAware() { return new
	 * AuditorAwareImpl(); }
	 */
	
	public static void main(String[] args) {
		SpringApplication.run(BookmarkApplication.class, args);
	}
	@Bean
	InitializingBean sendDatabase() {
	    return () -> {
	    	 //Tribe details
	    	  groupReferenceRepo.save(new GroupReference("TRIBE","RCM",  "Triber Rcm","bookmarkApp",new Date(System.currentTimeMillis()))); 
	    	  groupReferenceRepo.save(new GroupReference("TRIBE","FRM",  "Tribe Frm","bookmarkApp",new Date(System.currentTimeMillis()))); 
	    	  groupReferenceRepo.save(new GroupReference("TRIBE","EQD",  "Tribe Eqd","bookmarkApp",new Date(System.currentTimeMillis()))); 
	    	  
	    	  //Featured Team
			  groupReferenceRepo.save(new  GroupReference("FT","SNO", "Strcture and Oraganization","bookmarkApp",new Date(System.currentTimeMillis()))); 
			  groupReferenceRepo.save(new GroupReference("FT","ENO",  "Environmental and Others","bookmarkApp",new Date(System.currentTimeMillis()))); 
			  groupReferenceRepo.save(new GroupReference("FT","PNA", "Referntial Client Management","bookmarkApp",new Date(System.currentTimeMillis()))); 
			  groupReferenceRepo.save(new GroupReference("FT","KYC", "Know Your Customer","bookmarkApp",new Date(System.currentTimeMillis()))); 
			  groupReferenceRepo.save(new GroupReference("FT","CMT", "Cmt Feature team","bookmarkApp",new Date(System.currentTimeMillis()))); 
			  groupReferenceRepo.save(new GroupReference("FT","CTA", "Cta Feature team","bookmarkApp",new Date(System.currentTimeMillis()))); 
			  groupReferenceRepo.save(new GroupReference("FT","SCOW", "SGCIB Client Onboarding team","bookmarkApp",new Date(System.currentTimeMillis()))); 
			  groupReferenceRepo.save(new GroupReference("FT","ONS", "Ons FT","bookmarkApp",new Date(System.currentTimeMillis()))); 
			  groupReferenceRepo.save(new GroupReference("FT","ATS", "Ats Core","bookmarkApp",new Date(System.currentTimeMillis()))); 
			  groupReferenceRepo.save(new GroupReference("FT","NOS", "Nos Featured team","bookmarkApp",new Date(System.currentTimeMillis()))); 
			  
			  // Applications
			  groupReferenceRepo.save(new GroupReference("APPLICATION","BDR",  "Referential System","bookmarkApp",new Date(System.currentTimeMillis()))); 
			  groupReferenceRepo.save(new  GroupReference("APPLICATION","MAESTRO", "Maestro Application","bookmarkApp",new Date(System.currentTimeMillis()))); 
			  groupReferenceRepo.save(new  GroupReference("APPLICATION","TPS", "TPS Application","bookmarkApp",new Date(System.currentTimeMillis()))); 
			  groupReferenceRepo.save(new  GroupReference("APPLICATION","EDGE", "Edge Application","bookmarkApp",new Date(System.currentTimeMillis()))); 
			  groupReferenceRepo.save(new  GroupReference("APPLICATION","RISQ", "Risk Application","bookmarkApp",new Date(System.currentTimeMillis()))); 
			 
	      };
	   }

}
