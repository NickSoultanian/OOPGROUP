import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PSSMain {

        public static void main(String[] args) {
            //instantiating json parser and the scanner
            JSONParser jsonParser = new JSONParser();
            Scanner userInput = new Scanner(System.in);
            //prompting user to import new tasks
            System.out.println("Please enter the file to open: ");
            String userString = userInput.nextLine();

            //instantiate a scheduler object here!

            //start by opening a current calender json file
            ReadJsonFile(userString, jsonParser);
            userString = "y";
            String newUserString;
            while(userString.equals("y")) {
                System.out.println("Would you like to add more events? (y/n)");
                userString = userInput.nextLine();
                if (userString.equals("n")){
                    break;
                }

                System.out.println("Please enter the file to open: ");
                newUserString = userInput.nextLine();
                ReadJsonFile(newUserString, jsonParser);

            }



            //Save the current calender and what has been added.
            //I need to figure out how our scheduler is saving all of


        }

        private static void ReadJsonFile(String userString, JSONParser jsonParser){  //the scheduler object needs to be passed in as well.
            try {
                //using user-prompted text to access json file (in project folder)
                FileReader reader = new FileReader(userString + ".json");

                //Read JSON file and save to an object
                Object tempTask = jsonParser.parse(reader);

                //cast the object to store contents of JSON file into JSONArray
                JSONArray taskList = (JSONArray) tempTask;
                System.out.println(taskList); //test the JSONArray

                Object[] objArr;
                //call the ParseTaskObject method and pass in JSONArray, save to object array
                objArr = ParseTaskObject(taskList);

                //test for all objects in the array
                System.out.println(objArr[0] + "\n");
                //System.out.println(objArr[1] + "\n");
                //System.out.println(objArr[2] + "\n");
                //System.out.println(objArr[3] + "\n");

                /*pass these objects to the scheduler class and handle this
                            //check to see if time conflict (start time and duration and frequency), only if time is within startdate and enddate
                            //check to see if type is within bounds

                 */

                reader.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //method to parse the JSONArray into separate objects of a
        // JSONObject array for easier access to the objects' contents
        private static Object[] ParseTaskObject(JSONArray task)
        {
            //initialize counter and assign the passed JSONArray to a JSONObject array
            int i = 0;
            JSONObject[] taskObject = new JSONObject[task.size()];

            //while i is less than the size of the JSONArray, do:
            do{
                //assign a different object ID
                taskObject[i] = (JSONObject) task.get(i);

                //from here down, testing all content of each object
                String name = (String) taskObject[i].get("Name");
                System.out.println(name);

                String type = (String) taskObject[i].get("Type");
                System.out.println(type);

                Long startDate = (Long) taskObject[i].get("StartDate");
                System.out.println(startDate);

                Long startTime = (Long) taskObject[i].get("StartTime");
                System.out.println(startTime);

                Double duration = (Double) taskObject[i].get("Duration");
                System.out.println(duration);

                Long endDate = (Long) taskObject[i].get("EndDate");
                System.out.println(endDate);

                Long frequency = (Long) taskObject[i].get("Frequency");
                System.out.println(frequency);
                System.out.println();
                //increase flag counter by one
                i++;
            }while(i< task.size());
            //return the newly created array of JSON Objects
            return taskObject;
        }
    }
