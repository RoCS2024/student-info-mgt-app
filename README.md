Student-Info-Mgt-App Setup

First is setup the Database
There are 2 database you need for Student-info-mgt-app 
The Login Table and the other one is Student Table

Here is the Code for Login Table 
CREATE TABLE LOGIN (
    ID         	    NUMBER(32)NOT NULL PRIMARY KEY,
    USERNAME        VARCHAR2(64),
    PASSWORD   	    VARCHAR2(64),
    ENTITY_ID       VARCHAR2(32),
    DATE_CREATED    TIMESTAMP(6),
    DATE_MODIFIED   TIMESTAMP(6)
);

Here is the Code for Student Table
create table student (
    student_id          varchar2(32) not null primary key,
    last_name           varchar2(64) not null,
    first_name          varchar2(64) not null,
    middle_name         varchar2(64) not null,
    sex                 varchar2(8),
    birthday            timestamp(6),
    religion            varchar2(64),
    email               varchar2(64),
    address             varchar2(64),
    contact_number	varchar2(32));

Now that you are done with the database

You can now proceed to create jar in the Back-End of Student Info Management System

First go the Repository Back-end named "rc-sims" (student information management system)
Here is the  url code: 
https://github.com/RoCS2024/rc-ums.git

![rc-sims repo](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/2938c811-e898-4c29-8921-2571745d00ef)

Get the code
![code](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/3b5699ab-5f2f-469e-ab5e-b457626d4603)

Create New Folder

Open Git bash
type "git clone" and paste the url code 
![git clone](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/24dad393-81e2-4e8a-8c96-bac749560481)

Open the rc-sims in IntelliJ
![backend-rc-sims](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/ab942216-ceb7-4675-95b4-418df4d696eb)

Delete the Main Class
Right click main, select delete and then press okay
![deleteMainClass](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/dce6c041-ee05-4f2e-a5bc-34ae3f38268c)

After deleting the Main class

Go to ivy.xml Rename the module and then type "sims"
![ivyxml](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/e6de5c68-b0f5-4a09-b708-d6e4b9f8d774)

this part.
Rename the:
module="ivy-test" into module="sims"
![thispart](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/7cff935f-8886-4fb3-9863-e6d41de5537c)

like this
![renameSims](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/62ff6110-4dac-4015-8a8a-6ae27f472482)

Now you are done with ivy.xml

You can now proceed to build.xml
Go to build.xml and then go to create jar
![buildxml](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/f183d8b7-839f-488d-9ad3-c5a4f172e2d3)

rename the:
<jar destfile="${build.dir}/${ant.project.name}-${version}.jar" into <jar destfile="${build.dir}/sims.jar"

like this
![renameSimsBuildxml](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/afdf2cde-22a7-4008-84b6-c46222d4f668)

Now that you are done with ivy.xml and build.xml

Go to Terminal and then type "ant build-jar"
![antbuildjar](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/9b236c08-83dc-4816-849e-daa9b59e5d0a)

It will display build-jar Successful
![buildsuccessful](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/528bf6b5-9f3c-4ffa-8aa8-3f09d00ff0bd)

Check the build if the jar exist
![jarexist](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/3cd02ef7-c957-4dec-bdfa-15fe11a71c88)

And now you are done creating jar in the Back-End of Student Information Management System (sims)

You just need to repeat the steps in creating the jar for the User Management System (ums).

Now we are ready to import the sims jar and the ums jar but before that we need to set up the Front-end of the Student Information Management System.

First you go to the repo of Front-End Student Information Management System named "student-info-mgt-app"
Here is the url code:
https://github.com/RoCS2024/student-info-mgt-app.git

![repoStudent-info-mgt-app](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/13325d86-7efa-46c2-8214-c0b5bcf8e7e2)

Just like we did before you need to create Folder

Clone the url code of the student-info-mgt-app
![cloneStudent-info-mgt-app](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/a4b8cb68-1f2d-4947-9513-a9d02c8bc84d)


and then open it in the IntelliJ

When you open the student-info-mgt-app there are some possible error you will encounter.
1. First is the dependency
It looks like this
![possible error](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/e46e4a97-e763-4325-bc18-39925e208971)

To fix this first check the pom.xml
![image](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/51dbf73e-3c03-4556-a9de-17faa0b99653)

Check if these dependency are in the pom.xml
![dependency](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/83767191-5c76-48df-8fa4-28051f409957)

if not 
copy this dependency and then add it to pom.xml
 <dependency>
            <groupId>com.oracle.database.jdbc</groupId>
            <artifactId>ojdbc11</artifactId>
            <version>23.3.0.23.09</version>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-slf4j2-impl</artifactId>
            <version>2.23.1</version>
        </dependency>

after that go to Terminal type mvn clean install

2. The second possible error you will encounter is this:

REMINDER THIS IS OPTIONAL WHEN YOUR JAR IS NOT WORKING ON YOU FRONT-END
You will notice that the jar name of ums is umsv2 in my project but the original name of the jar is ums not umsv2 why did i change that into umsv2? because when you done creating jar in Back-end and when you import it in your Front End you will notice, even if you type the correct name of the jar in the module-info-java and the 2 jar are all set and when you run the Main View in the Front-end it will display "module not found ums or sims". 

To fix that you just need to delete the back-end of the jar that is not working
Create new folder
Clone it again
Repeat the steps on how to create Jar in the Back-End
Rename the jar just like i did in my project for example "umsv2"
and then it is ready to import to the Front End and i can guarantee that it will work.

3. The third error you will encounter when you open the Front-end of student-info-mgt-app is the "module-info-java"

As you can see in here the "require sims" part and "umsv2" is color red. Because we have not yet imported the jar.
![module-info-java](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/ad156c49-0f38-4f37-a3fa-d9958f21e650)

Now where going to import the 2 jar of the Back-end into the Front-end
Here is the step by step:
1. Go to Project Structure
![projectStructure](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/fd8b8288-a932-47af-bd83-bd00f80ba61f)
2. Go to Modules
![modules](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/98615701-bf39-4b16-85a4-cbe6d31ff5ca)
3.Click the plus button and then choose option 1. Jar or Directories
![addButton](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/7cc10cbb-0b1d-4088-ab40-fa22332f2a57)
4.After that locate the file where you build the jar. Click the Jar and press okay.
![locateJar](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/76f72a98-9ac6-4fb8-a43a-b1a3391739a2)
5.The jar that you select will add to your dependency.
![simsjar](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/795f457c-9eca-4c56-80e8-3648725e2829)
6.Repeat the steps when you want to import the jar ums
7.As you can see the 2 jar is in the dependency. Click okay and then it will resolve the error in the modules-info-java
![2jarimported](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/c3abecd9-b437-4a49-808c-0d8496cb2569)
8. As you can see the errors are gone 
![done](https://github.com/RoCS2024/student-info-mgt-app/assets/157860683/5f844b56-95cd-44d1-8671-7635116b97cd)

When you are done you can run the Main View of the Front End smoothly enjoy!!













