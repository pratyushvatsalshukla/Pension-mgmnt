# PensionManagementSystem-POD2
Pension Management System - IJ016

Different MicroServices are:
1. Process Pension MicroService
2. Pensioner Detail MicroService
3. Pension Disbursement MicroService
4. Authorization MicroService
5. Pension Management Portal

A. Authorization MicroSevice:

    This MicroService is to generate and validate the JWT token.
    
    a. To authenticate User:
         Step-1 : Select the POST method    
         Step-2 : Paste the URL  http://localhost:8084/authenticate  
         Step-3 : Select Body and then select raw. Now, choose JSON and paste the JSON object which is given below in the body.  
                   {     
                       "username" : "admin",                     
                       "password" : "admin"                       
                   }                   
         Step-4 : Click on Send. A JWT token will be generated. Now, copy that token and paste it in the Bearer token.
         
    b. To Validate the token generated:
         Step-1 : Select the POST method 
         Step-2 : Paste the URL http://localhost:8084/validate 
         Step-3 : Select Body and then select raw. Now, choose JSON and paste the JSON object which is given below in the body.
                   {
                       "token": " "    // Paste the token within " " 
                   }
         Step-4 : Click on Send. If the token is valid then it will return true.
         
         
B.  Pensioner Detail MicroService:

    This MicroService stores 20 Pensioner details in a CSV file. It will return the pensioner details based on AadharNumber.
     
     a. Correct Aadhar Number:
         Step-1 : select POST method
         Step-2 : Paste the URL http://localhost:8082/pensionerDetailByAadhaar/102233445566 . As this Aadhar Number is already present in the CSV file, we will get the Pensioner details with that particular Aadhar Number.
     
     b. Incorrect Aadhar Number:
          Step-1 : select POST method
          Step-2 : Paste the URL http://localhost:8082/pensionerDetailByAadhaar/102233445577 . As this Aadhar Number is not present in the CSV file, an exception will be thrown that the Pensioner with that particular Aadhar Number is not present.
                    
          
C. Process Pension MicroService:
   
    This MicroService calculates the pension amount of a particular person(based on the input given). This microservice invokes the Pensioner detail microservice and get the salary detail. Pension amount calculation detail is as follows : 
    Self pension: 80% of the last salary earned + allowances   
    Family pension: 50% of the last salary earned + allowances
    This microservice can have pre-defined list of banks and service charge as follows 
    Public banks – INR 500 
    Private banks – INR 550
    After calculating pension amount, particular pensioner details will be stored in h2database.
 
    a. Correct Details:
         Step-1 : Select the POST method
         Step-2 : Paste the URL http://localhost:8081/pensionerInput
         Step-3 : Select Body and then select raw. Now, choose JSON and paste the JSON object which is given below in the body.
                  {
	                  "name" : "Padmini",
	                  "dateOfBirth" : "2000-08-30",
	                  "pan" : "PCASD1234Q",
	                  "aadharNumber" : 102233445566,
	                  "pensionType" : "family"
	              }
                  
                  It will return a JSON object containing Aadhar Number, Pension Amount calculated and Service charge.
                  
    b. Incorrect Details:
         Step-1 : Select the POST method
         Step-2 : Paste the URL http://localhost:8081/pensionerInput
         Step-3 : Select Body and then select raw. Now, choose JSON and paste the JSON object which is given below in the body.
                  {
	                  "name" : "Padmini",
	                  "dateOfBirth" : "2000-08-30",
	                  "pan" : "PCASD1234Q",
	                  "aadharNumber" : 102233445577,
	                  "pensionType" : "family"
	              }
                  It will throw an exception as pensioner details are not valid.
                  
 D. Pension Disbursement MicroService:
 
    This MicroService receives the input of a pensioner like AadharNumber, Pension amount and Service Charges. This microservice can have pre-defined list of banks and service charge as follows:
        Public banks – INR 500 
        Private banks – INR 550
        It returns a StatusCode of "10" if the service charge given in the input is correct otherwise returns "21". If the StatusCode is 10 then only the Pension amount will be disbursed sucessfully to the pensioner bank account.
        
        a. Correct Pension Details with correct service charge:
            Step-1 : Select the POST method
            Step-2 : Paste the URL http://localhost:8083/disbursePension
            Step-3 : Select Body and then select raw. Now, choose JSON and paste the JSON object which is given below in the body.
                       {
	                       "aadharNumber" : 102233445566,
	                       "pensionAmount": 23950.0,
	                       "serviceCharge": 550
	                   }
                       
                       For the above mentioned pensioner, the type of bank is "private", the service charge given is correct. So, it will return "10" as StatusCode.
                       
        b. Correct Pension Details with incorrect service charge:
            Step-1 : Select the POST method
            Step-2 : Paste the URL http://localhost:8083/disbursePension
            Step-3 : Select Body and then select raw. Now, choose JSON and paste the JSON object which is given below in the body.
                       {
	                       "aadharNumber" : 102233445566,
	                       "pensionAmount": 23950.0,
	                       "serviceCharge": 500
	                   }
                       
                       For the above mentioned pensioner, the type of bank is "private", the service charge given is incorrect. So, it will return "21" as StatusCode.
                       
        c. Incorrect Pension Details:
            Step-1 : Select the POST method
            Step-2 : Paste the URL http://localhost:8083/disbursePension
            Step-3 : Select Body and then select raw. Now, choose JSON and paste the JSON object which is given below in the body.
                       {
	                       "aadharNumber" : 102233445577,
	                       "pensionAmount": 23950.0,
	                       "serviceCharge": 550
	                   }
                       
                       As the above mentioned pension details are incorrect, an exception is thrown.
                       
 E. Pension Management Portal MicroService:
     
      Step-1 : Open Chrome and paste the URL http://localhost:8085/portal/login
      Step-2 : Give UserName as "admin" and Password as "admin" and click on Login button. If you enter wrong details, it will display "Invalid Credentials".
      Step-3 : After successful login, enter the details of pensioner.
               Name: Padmini
               Date Of Birth: 30-08-2000
               PAN : PCASD1234Q
               Aadhar Number: 102233445566
               Pension Type: family
      Step-4 Click on Fetch Details button.         
               If details are correct, then it will calculate the pension amount and disaplys it and the details are stored in h2 database. But, if the details are incorrect, then it will display Wrong details.
      Step-4 : Now, click on Disburse Pension button. The pension amount will be disbursed to the pensioner bank account.    
      Step-5 : You can logout using Logout button.
                       

        

 

    
