# Update email configurations

src/main/java/com/example/agentportalbackend/AgentPortalBackendApplication.java

  

in this file update these feilds with your mail server details

  

    mailSender.setHost("sandbox.smtp.mailtrap.io");
    mailSender.setPort(2525);
    mailSender.setUsername("f9229aad8234ab");    
    mailSender.setPassword("1074b88c5b9587");

  
  

# Update db configurations

  Please update below fields in the mentioned path.
  
*/Users/madhushanihewagama/Documents/other/AgentPortal-50000/agent-portal-backend/src/main/resources/application.properties*



    spring.datasource.url=jdbc:mysql://localhost:3306/AgentPortalDB
    spring.datasource.username=root    
    spring.datasource.password=root2021*

  
  

# Create database

    Create database AgentPortalDB;

  

After start the application manually input the other roles

  

    insert into AgentPortalDB.database_manager(username,password,role,email) values ('BackendUser','BackendUser','BackendUser','BackendUser@life.in.com');
    
    insert into AgentPortalDB.database_manager(username,password,role,email) values ('AgencyManager','AgencyManager','AgencyManager','AgencyManager@@life.in.com');

