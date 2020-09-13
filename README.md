# Trigram
Create a trigram algorithm to generate text as per:
http://codekata.com/kata/kata14-tom-swift-under-the-milkwood/

Using the first and second chapter of Tom Swift and His Airship.
The file is in the `src/main/resources` directory.

To run via an IDE simply run the main class, `src/main/java/com/sridhar/TrigramAlgorithm.java`.
To run via command line build the jar with Maven:
`mvn clean install package`
Then run the following command:
`java -cp target/trigram-1.0.jar com.sridhar.TrigramAlgorithm`
This will create a file with the following file name format `TrigramOutput_<todays date and time>.txt` in the root directory of where it was ran.