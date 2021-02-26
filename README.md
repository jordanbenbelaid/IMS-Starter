Coverage: 80.02%
# IMS-Starter

The IMS-Starter project is an information system that aims to act as a way of storing orders, customers and items in a database.
Each part has the option to create, read, update and delete functions, with orders being able to have an item added and deleted from an order.
Orders contain a customer and items so that the user knows who has what item in their order, as well as a total price for the whole order.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Java Eclipse IDE
Maven
MySql workbench (optional)

### Installing

1. Clone repository to any folder you like in your device from branch: master
2. Open Eclipse IDE
3. Go to File ---> Open projects from File System, find IMS-Starter and click into it, and click finish (this will import the project into your Eclipse IDE so you can edit it as you wish)
4. Run 'mvn package' from the command line in the IMS-Starter folder
5. All set!

Check everything is running as should be!

## Running the tests

The testing has been done through JUnit and Mockito, which can be run through the IDE, following the steps in the 'Unit Tests' section below.

### Unit Tests 

To run the unit tests, right click on the project folder itself in the Eclipse IDE, and choose Coverage as ---> JUnit test

This will run all tests in the IMS folder, and show the coverages of each class in the main file.

## Deployment

Once mvn package has been run in the command line (from the ims folder itself) there will be a target folder in the IMS-Starter folder, which you then want to run the command line from inside here.

Once you are in the command line in this folder, run the command 'java -jar ims-0.0.1-jar-with-dependencies.jar file.

This will then open the project as in the command line as if you are running the project in the console in Eclipse.

(If you want to run it in eclipse, find the runner.java file in the com.qa.ims package as a java application and it will have the same effect)

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use git for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Jordan Benbelaid** - *Completed project* - [jordanbenbelaid](https://github.com/jordanbenbelaid)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to Edward Reynolds for the support!

