import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PSSMain {
        public static User user;

        public PSSMain(User user) {
                this.user = user;

        }

        public static void WriteJSonFile( List<Task> tasks, File userDefinedFile) {
                List<JSONObject> temp = convertTaskToJSON(tasks);
                try (FileWriter file = new FileWriter(userDefinedFile+".json"))
                {
                        file.write("[");
                        for(int i = 0; i<temp.size();i++){
                                file.write(temp.get(i).toString()+",\n");
                                System.out.println(temp.get(i).toString());
                        }
                        file.write("]");
                        file.flush();
                } catch (
                        IOException e) {
                        e.printStackTrace();
                }
        }

        public static List<JSONObject> convertTaskToJSON(List<Task> tasks){
                List<JSONObject> newList = new ArrayList<>();
                for(int i = 0; i< tasks.size(); i++){
                        if(tasks.get(i).getFrequency()!=0){
                                JSONObject newObject = new JSONObject();
                                String tempName = tasks.get(i).getName();
                                String tempType = tasks.get(i).getType();
                                int tempStartDate = tasks.get(i).getStartDate();
                                double tempStartTime = tasks.get(i).getStartTime();
                                double tempDuration = tasks.get(i).getDuration();
                                int tempFrequency = tasks.get(i).getFrequency();
                                System.out.println(tasks.get(i).getName() + " = getName");

                                newObject.put("Name",tempName);
                                newObject.put("Type",tempType);
                                newObject.put("StartDate",tempStartDate);
                                newObject.put("StartTime",tempStartTime);
                                newObject.put("Duration",tempDuration);
                                newObject.put("EndDate",tempStartDate+1);
                                newObject.put("Frequency",tempFrequency);

                                if(i == 0){
                                        newList.add(newObject);
                                }
                                newList.add(newObject);
                        }else{
                                JSONObject newObject = new JSONObject();
                                String tempName = tasks.get(i).getName();
                                String tempType = tasks.get(i).getType();
                                int tempStartDate = tasks.get(i).getStartDate();
                                double tempStartTime = tasks.get(i).getStartTime();
                                double tempDuration = tasks.get(i).getDuration();

                                newObject.put("Name",tempName);
                                newObject.put("Type",tempType);
                                newObject.put("StartDate",tempStartDate);
                                newObject.put("StartTime",tempStartTime);
                                newObject.put("Duration",tempDuration);

                                newList.add(newObject);
                        }
                }
                return newList;
        }


        public static void ReadJsonFile (String file, JSONParser jsonParser)
        {  //the scheduler object needs to be passed in as well.
                try {
                        //using user-prompted text to access json file (in project folder)
                        FileReader reader = new FileReader(file );

                        //Read JSON file and save to an object
                        Object tempTask = jsonParser.parse(reader);

                        //cast the object to store contents of JSON file into JSONArray
                        JSONArray taskList = (JSONArray) tempTask;
                        System.out.println(taskList); //test the JSONArray

                        //call the ParseTaskObject method and pass in JSONArray, save to object array
                        Object[] objArr = ParseTaskObject(taskList);


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
        public static Object[] ParseTaskObject (JSONArray task)
        {
                //initialize counter and assign the passed JSONArray to a JSONObject array
                int i = 0;
                JSONObject[] taskObject = new JSONObject[task.size()];

                //while i is less than the size of the JSONArray, do:
                do {
                        //assign a different object ID
                        taskObject[i] = (JSONObject) task.get(i);

                        //from here down, testing all content of each object
                        //  String nameTest = (String) task.get("name");
                        String name = (String) taskObject[i].get("Name");
                        System.out.println(name);

                        String type = (String) taskObject[i].get("Type");
                        System.out.println(type);
                        int startDate = 0;
                        if (((Number) taskObject[i].get("StartDate")) != null) {
                                startDate = ((Number) taskObject[i].get("StartDate")).intValue();
                                System.out.println(startDate);
                        } else if (taskObject[i].get("Date") != null) {
                                startDate = ((Number) taskObject[i].get("Date")).intValue();
                                System.out.println(startDate);
                        }


                        double startTime = ((Number) taskObject[i].get("StartTime")).doubleValue();
                        System.out.println(startTime);

                        double duration = ((Number) taskObject[i].get("Duration")).doubleValue();
                        System.out.println(duration);

                        int endDate = 0;
                        if (taskObject[i].get("EndDate") != null) {
                                endDate = ((Long) taskObject[i].get("EndDate")).intValue();
                                System.out.println(endDate);
                        }
                        int frequency = 0;
                        if (taskObject[i].get("Frequency") != null) {
                                frequency = ((Long) taskObject[i].get("Frequency")).intValue();
                                System.out.println(frequency);
                        }

                        if (frequency != 0) {
                                user.addrecurring(name, type, startDate, startTime, duration, endDate, frequency);
                        } else if (type.equals("Cancellation")) {
                                user.antitask(name, startDate, startTime, duration);
                        } else if (endDate == 0) {
                                user.addtransient(name, type, startDate, startTime, duration);
                        }

                        //increase flag counter by one
                        i++;
                } while (i < task.size());
                //return the newly created array of JSON Objects
                return taskObject;
        }
}
